package com.gni.frmk.tools.addon.service;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.configuration.Configuration;
import com.gni.frmk.tools.addon.configuration.component.*;
import com.gni.frmk.tools.addon.configuration.component.EnableState.EnableStatus;
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
        assertEquals(4, cnf.getNativeTriggerConfigurations().size());
    }

    @Test
    public void testSaveConfiguration()  {
        int indx = 0;
        ConfigurationService srv = new ConfigurationService(utils);
//        Configuration cnf = Configuration.builder()
//                                         .create("testSaveConfiguration", new Date())
//                                         .addAdapterConnection(createAdapterConnection(++indx))
//                                         .addAdapterConnection(createAdapterConnection(++indx))
//                                         .build();
//        cnf.getAdapterListenerList().add(createListenerConnection(++indx));
//        cnf.getAdapterListenerList().add(createListenerConnection(++indx));
//        cnf.getAdapterNotificationList().add(createNotificationConnection(++indx));
//        cnf.getAdapterNotificationList().add(createNotificationConnection(++indx));
//        cnf.getPortList().add(createPort(++indx));
//        cnf.getPortList().add(createPort(++indx));
//        cnf.getPortList().add(createPort(++indx));
//        cnf.getSchedulerList().add(createScheduler(++indx));
//        cnf.getSchedulerList().add(createScheduler(++indx));
//        cnf.getSchedulerList().add(createScheduler(++indx));
//        cnf.getJmsTriggerList().add(createJmsTrigger(++indx));
//        cnf.getJmsTriggerList().add(createJmsTrigger(++indx));
//        cnf.getNativeTriggerList().add(createNativeTrigger(++indx));
//        cnf.getNativeTriggerList().add(createNativeTrigger(++indx));
//        cnf.getNativeTriggerList().add(createNativeTrigger(++indx));
//        cnf.getNativeTriggerList().add(createNativeTrigger(++indx));
//        cnf.getJmsAliasList().add(createJmsAlias(++indx));
//        cnf.getJmsAliasList().add(createJmsAlias(++indx));
//        srv.saveConfiguration(cnf);
    }

//    private JmsAlias createJmsAlias(int indx) {
//        return JmsAlias.builder().define(String.format("alias essai %d", indx), null, ComponentState.EnableStatus.ENABLED, ActivableComponentState.ActiveStatus.SUSPENDED).build();
//    }

//    private NativeTrigger createNativeTrigger(int indx) {
//        NativeTrigger.NativeState s1 = new NativeTrigger.NativeState(Trigger.State.ACTIVE,
//                Trigger.TemporalStatus.TEMPORARY);
//        NativeTrigger.NativeState s2 = new NativeTrigger.NativeState(Trigger.State.SUSPENDED,
//                Trigger.TemporalStatus.PERMANENT);
//        return new NativeTrigger("triggerNatif" + indx, Trigger.Status.ENABLED, s1, s2);
//    }

//    private JmsTrigger createJmsTrigger(int indx) {
//        return new JmsTrigger("triggerJms" + indx, Trigger.Status.ENABLED, Trigger.State.SUSPENDED);
//    }

    private Scheduler createScheduler(int indx) {
        //TODO a corriger
        return null;
//        return new Scheduler("typeScheduler",
//                "nameScheduler" + indx,
//                "oidScheduler" + indx,
//                "srv" + indx,
//                Scheduler.ExecutionState.READY,
//                Scheduler.SchedulerState.UNEXPIRED);
    }

//    private Port createPort(int indx) {
//        return new PortBuilder().define("port" + indx, "WmRoot", ComponentState.EnableStatus.ENABLED, ActivableComponentState.ActiveStatus.SUSPENDED).build();
//    }

//    private AdapterNotification createNotificationConnection(int indx) {
//        return new AdapterNotificationBuilder("JDBCAdapter").define("notif" + indx, "pckgEssai" + indx, ComponentState.EnableStatus.ENABLED, ActivableComponentState.ActiveStatus.SUSPENDED).build();
//    }

    private AdapterConnection createAdapterConnection(int indx) {
        return  AdapterConnection.builder()
                                 .alias("aliasName" + indx)
                                 .adapterType("JDBCAdapter")
                                 .packageName("pckgEssai" + indx)
                                 .defineState(new EnableState(EnableStatus.ENABLED))
                                 .build();
    }

//    private AdapterListener createListenerConnection(int indx) {
//        return new AdapterListenerBuilder("JDBCAdapter").define("listener" + indx, "pckgEssai" + indx, ComponentState.EnableStatus.ENABLED, ActivableComponentState.ActiveStatus.SUSPENDED).build();
//    }
}
