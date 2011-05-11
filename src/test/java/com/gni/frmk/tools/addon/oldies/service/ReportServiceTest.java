package com.gni.frmk.tools.addon.oldies.service;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.util.PipelineTestUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.net.URISyntaxException;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 6 oct. 2010
 * Time: 11:02:11
 * To change this template use File | Settings | File Templates.
 */
public class ReportServiceTest {

    private static IntegrationServerUtil isUtils;
    private static PipelineTestUtils testUtils;

    @BeforeClass
    public static void setUp() throws URISyntaxException {
        testUtils = new PipelineTestUtils(ReportServiceTest.class);
        isUtils = mock(IntegrationServerUtil.class);
        when(isUtils.getCurrentPackageConfigDir()).thenReturn(testUtils.getTraceDirectory());

//        ServiceInvokerFactory fact = new ServiceInvokerFactory() {
//            public ServiceInvokerBuilder createServiceInvokerBuilder(String serviceName) {
//                ReplayServiceInvokerBuilder builder = new ReplayServiceInvokerBuilder(isUtils, serviceName, testUtils);
//                return builder;
//            }
//        };
//        rootJmsInvoker = new WmRootJmsInvoker(isUtils, fact);
//        artInvoker = new WmArtInvoker(isUtils, fact);
//        rootNativeInvoker = new WmRootNativeInvoker(isUtils, fact);
    }

    @AfterClass
    public static void tearDown() {
    }

//    @Test
//    public void testReportPortList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<Port> set = srv.reportPortList();
//        assertListSize(set, 10);
//        Port expectedPort = new PortBuilder().define("HTTPListener@7519", "WmRoot", ComponentState.EnableStatus.ENABLED, ActivableComponentState.ActiveStatus.SUSPENDED).build();
//        boolean found = false;
//        Port searchPort = null;
//        for (Port p : set) {
//            if (p.equals(expectedPort)) {
//                found = true;
//                break;
//            }
//        }
//        assertTrue("port not found", found);
//    }
//
//    @Test
//    public void testReportAdapterConnectionList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<AdapterConnection> set = srv.reportAdapterConnectionList();
//        assertListSize(set, 14);
//    }
//
//    @Test
//    public void testReportSchedulerList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<Scheduler> set = srv.reportSchedulerList();
//        assertListSize(set, 6);
//    }
//
//    @Test
//    @Ignore("need a SAP Adapter to test this case")
//    public void testReportAdapterListenerList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<AdapterListener> set = srv.reportAdapterListenerList();
//        assertListSize(set, -1);
//    }
//
//    @Test
//    public void testReportAdapterNotificationList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<AdapterNotification> set = srv.reportAdapterNotificationList();
//        assertListSize(set, 3);
//    }
//
//    @Test
//    public void testReportJmsAliasList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<JmsAlias> set = srv.reportJmsAliasList();
//        assertListSize(set, 9);
//    }
//
//    @Test
//    public void testReportJmsTriggerList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<JmsTrigger> set = srv.reportJmsTriggerList();
//        assertListSize(set, 2);
//    }
//
//    @Test
//    public void testReportNativeTriggerList() throws ServiceException {
//        ReportService srv = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
//        Set<NativeTrigger> set = srv.reportNativeTriggerList();
//        assertListSize(set, 67);
//    }

    private void assertListSize(Set<?> set, int size) {
        assertNotNull("list must not be null", set);
        assertTrue("list must not be empty", set.size() > 0);
        assertEquals("invalid list size", size, set.size());
    }

}
