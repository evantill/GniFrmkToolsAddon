package com.gni.frmk.tools.addon.handler.configuration.wm;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationSerializer;
import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationSerializer.SerializationException;
import com.gni.frmk.tools.addon.model.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTestRule;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Closeables;
import com.google.common.io.Files;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 13:51
 *
 * @author: e03229
 */
public class ISLocalConfigurationRepositoryTest {
//
//    @Rule
//    public ConfigurationTestRule testUtil = new ConfigurationTestRule();
//
//    private static ConfigurationSerializer serializer;
//    private static final String simpleXml = ConfigurationTestRule.loadXml("simple", ConfigurationTest.class);
//
//    @BeforeClass
//    public static void initSerializer() throws SerializationException {
//        serializer = new ConfigurationSerializer();
//    }
//
//    private IntegrationServerUtil mockIsUtil(File tmpDir, int nbrPkg, boolean initContent) throws IOException {
//        Configuration defaultCnf = serializer.loadConfiguration(new StringReader(simpleXml));
//        String[] packages = new String[nbrPkg];
//        for (int i = 0; i < packages.length; i++) {
//            packages[i] = String.format("packageName%d", i + 1);
//        }
//        IntegrationServerUtil isutil = Mockito.mock(IntegrationServerUtil.class);
//        Mockito.when(isutil.getPackageNameList()).thenReturn(Arrays.asList(packages));
//        final Map<String, File> configDirMap = Maps.newHashMap();
//        configDirMap.put("WmRoot", new File(new File(tmpDir, "WmRoot"), "config"));
//        for (int i = 0; i < packages.length; i++) {
//            String pkg = packages[i];
//            File pkgDir = new File(tmpDir, pkg);
//            configDirMap.put(pkg, new File(pkgDir, "config"));
//            if (initContent) {
//                File conf = new File(pkgDir, String.format("config/addon/configuration_file_name_%d.xml", i + 1));
//                Files.createParentDirs(conf);
//                ImmutableConfiguration cnf = ImmutableConfiguration.builder()
//                                                 .from(defaultCnf)
//                                                 .create(pkg, String.format("configuration_file_name_%d",
//                                                         i + 1), testUtil.now())
//                                                 .build();
//                FileOutputStream out = new FileOutputStream(conf);
//                try {
//                    serializer.saveConfiguration(cnf, out);
//                } finally {
//                    Closeables.closeQuietly(out);
//                }
//
//            }
//        }
//        Mockito.when(isutil.getPackageConfigDirectory(Mockito.<String>any())).thenAnswer(new Answer<File>() {
//            @Override
//            public File answer(InvocationOnMock invocation) throws Throwable {
//                String packageName = invocation.getArguments()[0].toString();
//                return configDirMap.get(packageName);
//            }
//        });
//        return isutil;
//    }
//
//    @Test
//    public void testRefresh() throws Exception {
//        File tmpDir = Files.createTempDir();
//        try {
//            ISLocalConfigurationRepository repo = new ISLocalConfigurationRepository(mockIsUtil(tmpDir, 10, true), serializer);
//            repo.refresh();
//            Assert.assertEquals(10, repo.getConfigurationList().size());
//        } finally {
//            Files.deleteRecursively(tmpDir);
//        }
//    }
//
//    @Test
//    public void testGetConfigurationList() throws Exception {
//        File tmpDir = Files.createTempDir();
//        try {
//            ISLocalConfigurationRepository repo = new ISLocalConfigurationRepository(mockIsUtil(tmpDir, 10, true), serializer);
//            Set<String> configurations = repo.getConfigurationList();
//            Assert.assertEquals(10, configurations.size());
//            Assert.assertTrue(configurations.contains("configuration_file_name_9"));
//        } finally {
//            Files.deleteRecursively(tmpDir);
//        }
//    }
//
//    @Test
//    public void testSaveConfiguration() throws Exception {
//        File tmpDir = Files.createTempDir();
//        try {
//            ISLocalConfigurationRepository repo = new ISLocalConfigurationRepository(mockIsUtil(tmpDir, 10, true), serializer);
//            String KEY = "configuration_file_name_9";
//            ImmutableConfiguration cnf = repo.loadConfiguration(KEY);
//            ImmutableConfiguration modified = ImmutableConfiguration.builder()
//                                                  .from(cnf)
//                                                  .create("WmRoot", "default", testUtil.now())
//                                                  .build();
//            repo.saveConfiguration(modified);
//            File expected = new File(tmpDir, "WmRoot/config/addon/default.xml");
//            Assert.assertTrue(expected.exists());
//            String expectedContent = Files.toString(expected, Charsets.UTF_8);
//            Assert.assertTrue(expectedContent.length() > 32);
//        } finally {
//            Files.deleteRecursively(tmpDir);
//        }
//    }
//
//    @Test
//    public void testLoadConfiguration() throws Exception {
//        File tmpDir = Files.createTempDir();
//        try {
//            ISLocalConfigurationRepository repo = new ISLocalConfigurationRepository(mockIsUtil(tmpDir, 10, true), serializer);
//            String KEY = "configuration_file_name_9";
//            ImmutableConfiguration cnf = repo.loadConfiguration(KEY);
//            Assert.assertEquals(1, cnf.getAdapterConnectionConfigurations().size());
//            Assert.assertEquals(KEY, cnf.getName());
//        } finally {
//            Files.deleteRecursively(tmpDir);
//        }
//    }
//
//    @Test
//    public void testPostload() throws Exception {
//        File tmpDir = Files.createTempDir();
//        try {
//            ISLocalConfigurationRepository repo = new ISLocalConfigurationRepository(mockIsUtil(tmpDir, 10, true), serializer);
//            Assert.assertEquals(10, repo.getConfigurationList().size());
//            repo.postunload("packageName5");
//            Assert.assertEquals(9, repo.getConfigurationList().size());
//            try{
//                repo.loadConfiguration("configuration_file_name_5");
//                Assert.fail("exception must be raised");
//            }catch(IllegalStateException expected){
//            }
//            repo.postload("packageName5");
//            Assert.assertNotNull(repo.loadConfiguration("configuration_file_name_5"));
//        } finally {
//            Files.deleteRecursively(tmpDir);
//        }
//    }
//
//    @Test(expected = IllegalStateException.class)
//    public void testPostunload() throws Exception {
//        File tmpDir = Files.createTempDir();
//        try {
//            ISLocalConfigurationRepository repo = new ISLocalConfigurationRepository(mockIsUtil(tmpDir, 10, true), serializer);
//            Assert.assertEquals(10, repo.getConfigurationList().size());
//            repo.postunload("packageName5");
//            Assert.assertEquals(9, repo.getConfigurationList().size());
//            repo.loadConfiguration("configuration_file_name_5");
//            Assert.fail("configuration_file_name_5 must not be found");
//        } finally {
//            Files.deleteRecursively(tmpDir);
//        }
//    }

}
