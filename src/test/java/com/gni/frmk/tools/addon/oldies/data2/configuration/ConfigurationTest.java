package com.gni.frmk.tools.addon.oldies.data2.configuration;

import com.gni.frmk.tools.addon.model.Component.Type;
import com.gni.frmk.tools.addon.model.Configuration;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationTest {

    private static File testDir;
    private static File loadConfigurationFile;
    private static File saveConfigurationFile;
    private static final String LOAD_CONFIGURATION_NAME = "loadConfiguration";
    private static final String SAVE_CONFIGURATION_NAME = "saveConfiguration";

    @BeforeClass
    public static void setUp() {
        testDir = new File("./target/datas");
        if (!testDir.exists()) {
            testDir.mkdir();
        }
        setUpLoadConfigurationFile();
        setUpSaveConfigurationFile();
    }

    private static void setUpLoadConfigurationFile() {
        String resName = String.format("%s/%s.xml", ConfigurationTest.class.getSimpleName(), LOAD_CONFIGURATION_NAME);
        InputStream in = ConfigurationTest.class.getResourceAsStream(resName);
        try {
            loadConfigurationFile = new File(testDir, String.format("%s.xml", LOAD_CONFIGURATION_NAME));
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

    private static void setUpSaveConfigurationFile() {
        String resName = String.format("%s/%s.xml", ConfigurationTest.class.getSimpleName(), SAVE_CONFIGURATION_NAME);
        try {
            saveConfigurationFile = new File(testDir, String.format("%s.xml", SAVE_CONFIGURATION_NAME));
            if (saveConfigurationFile.exists()) {
                assert saveConfigurationFile.delete();
            }
            assert saveConfigurationFile.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void tearDown() {
        //TODO delete directory and files
    }

    @Test
    public void testLoadConfiguration() throws JAXBException, FileNotFoundException {
        JAXBContext ctx = JAXBContext.newInstance(Configuration.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        FileReader in = new FileReader(loadConfigurationFile);
        Configuration configuration = (Configuration) unmarshaller.unmarshal(in);
        assertNotNull(configuration);
        assertEquals(4, configuration.listComponentConfigurationsByType(Type.NATIVE_TRIGGER).size());
    }

//    @Test
//    public void testSaveConfiguration() throws JAXBException, IOException {
//        int indx = 0;
//        Configuration cnf = new Configuration("testSaveConfiguration");
//        cnf.getAdapterConnections().add(createAdapterConnection(++indx));
//        cnf.getAdapterConnections().add(createAdapterConnection(++indx));
//        cnf.getAdapterListeners().add(createAdapterListener(++indx));
//        cnf.getAdapterListeners().add(createAdapterListener(++indx));
//        cnf.getAdapterNotificationList().add(createAdapterNotification(++indx));
//        cnf.getAdapterNotificationList().add(createAdapterNotification(++indx));
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
//
//        //TODO save as string for assert
//        {
//            final JAXBContext ctx = JAXBContext.newInstance(Configuration.class);
//            final Marshaller marshaller = ctx.createMarshaller();
//            final FileWriter out = new FileWriter(saveConfigurationFile);
//            marshaller.marshal(cnf, out);
//        }
//        //TODO a effacer
//        {
//            JAXBContext ctx2 = JAXBContext.newInstance(Configuration.class);
//            Marshaller marshaller2 = ctx2.createMarshaller();
//            marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            StringWriter out2 = new StringWriter();
//            marshaller2.marshal(cnf, out2);
//            System.out.println("out2:\n " + out2.toString());
//        }
//
//    }

//    private JmsAlias createJmsAlias(int indx) {
//        return JmsAlias.builder().id("JmsAlias" + indx).description("description").state(new EnableComponentState(EnableStatus.ENABLED)).check().build();
//    }
//
//    private JmsTrigger createJmsTrigger(int indx) {
//        return JmsTrigger.builder().id("JmsTrigger" + indx).state(new EnableComponentState(EnableStatus.ENABLED)).check().build();
//    }
//
//    private NativeTrigger createNativeTrigger(int indx) {
//        return NativeTrigger.builder().id("NativeTrigger" + indx).state(new EnableComponentState(EnableStatus.ENABLED)).check().build();
//    }
//
//    private Port createPort(int indx) {
//        return Port.builder()
//                   .id("Port" + indx)
//                   .packageName("pkgEssai")
//                   .state(new EnableComponentState(EnableStatus.ENABLED))
//                   .check()
//                   .build();
//    }

    //    private JmsAlias createJmsAlias(int indx) {
    //        return new JmsAliasBuilder().define(String.format("alias essai %d", indx), null, ComponentState.EnableStatus.ENABLED, ActivableComponentState.ActiveStatus.SUSPENDED).build();
    //    }
    //
    //    private NativeTrigger createNativeTrigger(int indx) {
    //        NativeTrigger.NativeState s1 = new NativeTrigger.NativeState(Trigger.State.ACTIVE,
    //                Trigger.TemporalStatus.TEMPORARY);
    //        NativeTrigger.NativeState s2 = new NativeTrigger.NativeState(Trigger.State.SUSPENDED,
    //                Trigger.TemporalStatus.PERMANENT);
    //        return new NativeTrigger("triggerNatif" + indx, Trigger.Status.ENABLED, s1, s2);
    //    }
    //
    //    private JmsTrigger createJmsTrigger(int indx) {
    //        return new JmsTrigger("triggerJms" + indx, Trigger.Status.ENABLED, Trigger.State.SUSPENDED);
    //    }

//    private Scheduler createScheduler(int indx) {
//
//        return Scheduler.builder()
//                        .schedulerName("SchedulerName" + indx)
//                        .schedulerType("SchedulerType" + indx)
//                        .id("SchedulerId" + indx)
//                        .service("SchedulerService" + indx)
//                        .state(new EnableComponentState(EnableStatus.ENABLED))
//                        .check()
//                        .build();
//    }
//
//    private AdapterConnection createAdapterConnection(int indx) {
//        return AdapterConnection.builder()
//                                .adapterType("JDBCAdapter")
//                                .packageName("pckgEssai")
//                                .alias("aliasName" + indx)
//                                .state(new EnableComponentState(EnableStatus.ENABLED))
//                                .check()
//                                .build();
//    }
//
//    private AdapterListener createAdapterListener(int indx) {
//        return AdapterListener.builder()
//                              .adapterType("JDBCAdapter")
//                              .packageName("pckgEssai")
//                              .listenerName("listenerName" + indx)
//                              .state(new EnableComponentState(EnableStatus.ENABLED))
//                              .check()
//                              .build();
//    }
//
//    private AdapterNotification createAdapterNotification(int indx) {
//        return AdapterNotification.builder()
//                                  .adapterType("JDBCAdapter")
//                                  .notificationName("notificationName" + indx)
//                                  .packageName("pckgEssai")
//                                  .state(new EnableComponentState(EnableStatus.ENABLED))
//                                  .check()
//                                  .build();
//    }

}
