package com.gni.frmk.tools.addon.oldies.service;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.util.PipelineTestUtils;
import com.gni.frmk.tools.addon.oldies.invoke.WmArtInvoker;
import com.gni.frmk.tools.addon.oldies.invoke.WmRootInvoker;
import com.gni.frmk.tools.addon.oldies.invoke.WmRootJmsInvoker;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 6 oct. 2010
 * Time: 11:34:31
 * To change this template use File | Settings | File Templates.
 */
public class PrepareReportServiceTest {

    private static IntegrationServerUtil isUtils;
    private static PipelineTestUtils testUtils;
    private static WmRootJmsInvoker rootJmsInvoker;
    private static WmRootInvoker rootNativeInvoker;
    private static WmArtInvoker artInvoker;

    @BeforeClass
    public static void setUp() {
        testUtils = new PipelineTestUtils(PrepareReportServiceTest.class);
        isUtils = mock(IntegrationServerUtil.class);
        when(isUtils.getCurrentPackageConfigDir()).thenReturn(testUtils.getTraceDirectory());

//        ServiceInvokerFactory fact = new ServiceInvokerFactory() {
//            public ServiceInvokerBuilder createServiceInvokerBuilder(String serviceName) {
//                RecordServiceInvokerBuilder builder = new RecordServiceInvokerBuilder(isUtils, serviceName, testUtils);
//                builder.defineRemoteServer("ar2dv102.groupe.generali.fr:5550", "claurent", "claurent");
//                return builder;
//            }
//        };
//        rootJmsInvoker = new WmRootJmsInvoker(isUtils, fact);
//        artInvoker = new WmArtInvoker(isUtils, fact);
//        rootNativeInvoker = new WmRootNativeInvoker(isUtils, fact);
    }

    @AfterClass
    public static void tearDown() {
        //TODO disconnect all sessions
    }

//    @Test
//    public void testReportPortList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<Port> set = srv.reportPortList();
//        assertNotEmpty(set);
//    }
//
//    @Test
//    public void testReportAdapterConnectionList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<AdapterConnection> set = srv.reportAdapterConnectionList();
//        assertNotEmpty(set);
//    }
//
//    @Test
//    public void testReportSchedulerList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<Scheduler> set = srv.reportSchedulerList();
//        assertNotEmpty(set);
//    }
//
//    @Test
//    @Ignore("need a SAP Adapter to test this case")
//    public void testReportAdapterListenerList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<AdapterListener> set = srv.reportAdapterListenerList();
//        assertNotEmpty(set);
//    }
//
//    @Test
//    public void testReportAdapterNotificationList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<AdapterNotification> set = srv.reportAdapterNotificationList();
//        assertNotEmpty(set);
//    }
//
//    @Test
//    public void testReportJmsAliasList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<JmsAlias> set = srv.reportJmsAliasList();
//        assertNotEmpty(set);
//    }
//
//    @Test
//    public void testReportJmsTriggerList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<JmsTrigger> set = srv.reportJmsTriggerList();
//        assertNotEmpty(set);
//    }
//
//
//    @Test
//    public void testReportNativeTriggerList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<NativeTrigger> set = srv.reportNativeTriggerList();
//        assertNotEmpty(set);
//    }
//
//    @Test
//    public void testReportCurrentConfiguration() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Configuration conf = srv.reportCurrentConfiguration("testReportCurrentConfiguration");
//        ConfigurationService cs = new ConfigurationService(isUtils);
//        cs.saveConfiguration(conf);
//    }
//
    private void assertNotEmpty(Set<?> Set) {
        assertNotNull("Set must not be null", Set);
        assertTrue("Set must not be empty", Set.size() > 0);
    }

}
