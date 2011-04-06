package com.gni.frmk.tools.addon.manager.wm;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.manager.ConfigurationRepository;
import com.gni.frmk.tools.addon.manager.ConfigurationSerializer;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Closeables;
import com.google.common.io.Files;
import com.wm.app.b2b.server.PackageListener;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Arrays.asList;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 10:07
 *
 * @author: e03229
 */
public class ISLocalConfigurationRepository implements ConfigurationRepository, PackageListener {

    private static final String ADDON_CONFIG_DIR = "addon";
    private static final String FILE_EXTENSION = ".xml";

    private final IntegrationServerUtil util;
    private final ConfigurationSerializer serializer;
    private final Map<String, ConfigurationContext> repository;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final class ConfigurationContext {
        private final String id;
        private final String packageName;
        private final File configurationFile;
        private Configuration lazyConfiguration;

        private ConfigurationContext(String id, String packageName, File configurationFile) {
            this.id = id;
            this.packageName = packageName;
            this.configurationFile = configurationFile;
        }

        private ConfigurationContext(String id, String packageName) {
            this.id = id;
            this.packageName = packageName;
            configurationFile = new File(addonPkgConfDirectory(packageName), idToFileName(id));
        }

        public String getId() {
            return id;
        }

        public String getPackageName() {
            return packageName;
        }

        public File getConfigurationFile() {
            return configurationFile;
        }

        public synchronized void removeConfiguration() throws IOException {
            if (configurationFile.exists()) {
                boolean deleted = configurationFile.delete();
                if (!deleted) {
                    throw new IllegalStateException(String.format("can not delete file %s", configurationFile));
                }
            }
        }

        public synchronized Configuration loadConfiguration() throws IOException {

            FileInputStream in = new FileInputStream(configurationFile);
            try {
                lazyConfiguration = serializer.loadConfiguration(in);
            } finally {
                Closeables.closeQuietly(in);
            }
            return lazyConfiguration;
        }

        public synchronized void saveConfiguration() throws IOException {
            checkNotNull(lazyConfiguration);
            if (!configurationFile.getParentFile().exists()) {
                Files.createParentDirs(configurationFile);
            }
            FileOutputStream out = new FileOutputStream(configurationFile);
            try {
                serializer.saveConfiguration(lazyConfiguration, out);
                out.flush();
            } finally {
                Closeables.closeQuietly(out);
            }
        }

        public void setConfiguration(Configuration cnf) {
            lazyConfiguration = cnf;
            try {
                saveConfiguration();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public Configuration getConfiguration() {
            if (lazyConfiguration == null) {
                try {
                    loadConfiguration();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
            return lazyConfiguration;
        }

    }

    private final FileFilter FILTER_CONFIGURATION_FILES = new FileFilter() {
        @Override
        public boolean accept(File fic) {
            return fic.isFile() && fic.canRead() && fic.getName().endsWith(".xml");
        }
    };

    public ISLocalConfigurationRepository(IntegrationServerUtil util, ConfigurationSerializer serializer) {
        this.util = util;
        this.serializer = serializer;
        repository = Maps.newHashMap();
        refresh();
    }

    @Override
    public void refresh() {
        lock.writeLock().lock();
        try {
            repository.clear();
            //loop on packages
            for (String packageName : util.getPackageNameList()) {
                loadPackageInRepository(packageName);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Les configurations sont enregistrées dans le répertoire config/addon des packages
     *
     * @return
     */
    @Override
    public Set<String> getConfigurationList() {
        lock.readLock().lock();
        try {
            return Collections.unmodifiableSet(repository.keySet());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void saveConfiguration(Configuration cnf) {
        String key = cnf.getId();
        boolean newEntry = false;
        ConfigurationContext ctx;
        lock.readLock().lock();
        try {
            ctx = repository.get(key);
            if (ctx == null) {
                newEntry = true;
                ctx = new ConfigurationContext(key, cnf.getPackageName());
            }
        } finally {
            lock.readLock().unlock();
        }
        //save file
        ctx.setConfiguration(cnf);
        //add entry in repository if needed
        if (newEntry) {
            lock.writeLock().lock();
            try {
                repository.put(key, ctx);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    @Override
    public Configuration loadConfiguration(String key) {
        lock.readLock().lock();
        try {
            ConfigurationContext ctx = repository.get(key);
            if (ctx != null) {
                return ctx.getConfiguration();
            } else {
                throw new IllegalStateException(String.format("configuration %s not found", key));
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    private void loadPackageInRepository(String packageName) {
        lock.writeLock().lock();
        try {
            File addonDir = addonPkgConfDirectory(packageName);
            if (addonDir.exists() && addonDir.isDirectory()) {
                List<File> files = asList(addonDir.listFiles(FILTER_CONFIGURATION_FILES));
                //loop on files
                for (File fic : files) {
                    String id = fileNameToId(fic.getName());
                    ConfigurationContext ctx = new ConfigurationContext(id, packageName, fic);
                    repository.put(id, ctx);
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    private File addonPkgConfDirectory(String packageName) {
        return new File(util.getPackageConfigDirectory(packageName), ADDON_CONFIG_DIR);
    }

    /**
     * Generate file name based on configuration id.
     * @param id the id of the configuration
     * @return the filename
     */
    private String idToFileName(String id) {
        return String.format("%s%s", id, FILE_EXTENSION);
    }

    /**
     * Extract the configuration id from the filename :
     * <ol>
     * <li>remove the extension (.xml)</li>
     * </ol>
     *
     * @param fileName the configuration file name.
     * @return the id for this configuration file
     */
    private String fileNameToId(String fileName) {
        return fileName.substring(0, fileName.length() - FILE_EXTENSION.length());
    }

    private void removePackageFromRepository(String packageName) {
        Set<String> keysToRemove = Sets.newHashSet();
        //list entries to remove
        lock.readLock().lock();
        try {
            for (Entry<String, ConfigurationContext> entry : repository.entrySet()) {
                if (entry.getValue().getPackageName().equals(packageName)) {
                    keysToRemove.add(entry.getKey());
                }
            }
        } finally {
            lock.readLock().unlock();
        }
        //remove from set
        lock.writeLock().lock();
        try {
            repository.keySet().removeAll(keysToRemove);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * add configuration files stored in package directory
     *
     * @param packageName
     * @throws Exception
     */
    @Override
    public void postload(String packageName) throws Exception {
        removePackageFromRepository(packageName);
        loadPackageInRepository(packageName);
    }

    /**
     * remove configuration file from package directory
     *
     * @param packageName
     * @throws Exception
     */
    @Override
    public void postunload(String packageName) throws Exception {
        removePackageFromRepository(packageName);
    }

    /**
     * unused
     *
     * @param packageName
     * @throws Exception
     */
    @Override
    public void preload(String packageName) throws Exception {}

    /**
     * unused
     *
     * @param packageName
     * @throws Exception
     */
    @Override
    public void preunload(String packageName) throws Exception {
    }

}
