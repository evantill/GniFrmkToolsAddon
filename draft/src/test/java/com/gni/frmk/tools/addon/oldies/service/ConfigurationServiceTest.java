package com.gni.frmk.tools.addon.oldies.service;

import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.oldies.services.ConfigurationService;
import com.gni.frmk.tools.addon.util.IntegrationServerUtil;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 30 sept. 2010
 * Time: 16:48:47
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationServiceTest {

    private static IntegrationServerUtil utils;
    private static File testDir;
    private static File loadConfigurationFile;
    private static final String LOAD_CONFIGURATION_NAME = "loadConfiguration";

    @BeforeClass
    public static void setUp() {
        testDir = new File("./target/datas");
        if (!testDir.exists()) {
            testDir.mkdir();
        }
        utils = mock(IntegrationServerUtil.class);
        when(utils.getCurrentPackageConfigDir()).thenReturn(testDir);
        setUpLoadConfigurationFile();
    }

    private static void setUpLoadConfigurationFile() {
        String resName = String.format("%s/%s.xml",
                ConfigurationServiceTest.class.getSimpleName(),
                LOAD_CONFIGURATION_NAME);
        InputStream in = ConfigurationServiceTest.class.getResourceAsStream(resName);
        try {
            File loadConfigurationFile = new File(testDir, String.format("%s.xml", LOAD_CONFIGURATION_NAME));
            FileOutputStream out = new FileOutputStream(loadConfigurationFile);
            try {
                ByteStreams.copy(in, out);
            } finally {
                Closeables.closeQuietly(out);
            }
        } catch (IOException e) {
            throw new IllegalStateException(String.format("can not open file %s", loadConfigurationFile));
        } finally {
            Closeables.closeQuietly(in);
        }
    }

    @AfterClass
    public static void tearDown() {
        //TODO delete directory and files
    }

    @Test
    public void testLoadConfiguration()  {
        ConfigurationService srv = new ConfigurationService(utils);
        Configuration cnf = srv.loadConfiguration(LOAD_CONFIGURATION_NAME);
        assertNotNull(cnf);
        assertEquals(4, cnf.listComponentConfigurationsByType(Type.NATIVE_TRIGGER).size());
    }


}
