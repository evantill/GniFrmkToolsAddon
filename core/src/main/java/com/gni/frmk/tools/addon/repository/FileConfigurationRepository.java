package com.gni.frmk.tools.addon.repository;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;
import com.gni.frmk.tools.addon.util.IntegrationServerUtil;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 16:40
 *
 * @author: e03229
 */
public class FileConfigurationRepository implements ConfigurationRepository {

    private final ConfigurationSerializer serializer;
    private final IntegrationServerUtil util;

    private static final String EXTENSION = ".xml";
    private static final String TOOLS_CONFIGURATION_DIR = "addon";

    private final FileFilter FILTER_CONFIGURATION_FILES = new FileFilter() {
        @Override
        public boolean accept(File fic) {
            return fic.isFile() && fic.canRead() && fic.getName().endsWith(EXTENSION);
        }
    };

    public FileConfigurationRepository(ConfigurationSerializer serializer, IntegrationServerUtil util) {
        this.serializer = serializer;
        this.util = util;
    }

    @Override
    public Set<ConfigurationId> getConfigurationList() {
        Set<ConfigurationId> configurations = Sets.newHashSet();
        for (String packageName : util.getPackageNameList()) {
            configurations.addAll(getConfigurationListFromPackage(packageName));
        }
        return configurations;
    }

    private Set<ConfigurationId> getConfigurationListFromPackage(String packageName) {
        Set<ConfigurationId> configurations = Sets.newHashSet();
        File toolConfigDir = toolsConfigDirFromPackage(packageName);

        if (toolConfigDir.exists()) {
            for (File fic : toolConfigDir.listFiles(FILTER_CONFIGURATION_FILES)) {
                configurations.add(new ConfigurationId(fileNameToId(fic.getName()), packageName));
            }
        }
        return configurations;
    }

    private File toolsConfigDirFromPackage(String packageName) {
        File configDir = util.getPackageConfigDirectory(packageName);
        return new File(configDir, TOOLS_CONFIGURATION_DIR);
    }

    /**
     * remove '.xml' and replace underscores by spaces
     *
     * @param fileName
     * @return the configuration id related to this file
     */
    private String fileNameToId(String fileName) {
        return fileName.substring(0, fileName.length() - EXTENSION.length()).replace('_', ' ');
    }

    /**
     * remove '/', '\','.'
     *
     * @param id
     * @return
     */
    private String idToFileName(String id) {
        String cleanId = id.replace('\\', ' ').replace('/', ' ').replace('.', ' ').trim().replace(' ', '_');
        StringBuilder builder = new StringBuilder(cleanId);
        builder.append(EXTENSION);
        return builder.toString();
    }

    @Override
    public void saveConfiguration(Configuration cnf) {
        String packageName = cnf.getId().getPackageName();
        if (!util.isPackageEnabled(packageName)) {
            throw new IllegalStateException(String.format("package %s not found", packageName));
        }
        File cnfDir = toolsConfigDirFromPackage(packageName);
        File cnfFile = new File(cnfDir, idToFileName(cnf.getId().getId()));
        if (!cnfDir.exists()) {
            try {
                Files.createParentDirs(cnfFile);
            } catch (IOException e) {
                throw new IllegalStateException(String.format("can not create directory %s", cnfDir), e);
            }
        }
        try {
            saveToFile(cnf, cnfFile);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("can not save file %s", cnfFile), e);
        }
    }

    @Override
    public Configuration loadConfiguration(ConfigurationId id) {
        File configurationFile = locateConfiguration(id);
        try {
            return loadFromFile(configurationFile);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("can not read file %s", configurationFile));
        }
    }

    @Override
    public boolean deleteConfiguration(ConfigurationId id) {
        File fic = locateConfiguration(id);
        if (fic != null && fic.exists() && fic.canRead()) {
            return fic.delete();
        } else {
            return false;
        }
    }

    private File locateConfiguration(ConfigurationId id) {
        File fic = locateConfigurationInPackage(id.getId(), id.getPackageName());
        if (fic != null && fic.canRead()) {
            return fic;
        }
        throw new IllegalStateException(String.format("configuration file %s not found", id));
    }

    private File locateConfigurationInPackage(String id, String packageName) {
        File toolConfigDir = toolsConfigDirFromPackage(packageName);
        return new File(toolConfigDir, idToFileName(id));
    }

    private void saveToFile(Configuration cnf, File file) throws IOException {
        FileWriter out = new FileWriter(file);
        try {
            serializer.saveConfiguration(cnf, out);
        } finally {
            out.close();
        }
    }

    private Configuration loadFromFile(File file) throws IOException {
        FileReader in = new FileReader(file);
        try {
            return serializer.loadConfiguration(in);
        } finally {
            in.close();
        }
    }

}
