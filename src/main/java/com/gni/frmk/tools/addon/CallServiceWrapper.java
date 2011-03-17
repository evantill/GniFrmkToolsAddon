package com.gni.frmk.tools.addon;

import com.gni.frmk.tools.addon.data.Configuration;
import com.gni.frmk.tools.addon.invoke.divers.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootNativeInvoker;
import com.gni.frmk.tools.addon.service.AdminService;
import com.gni.frmk.tools.addon.service.ConfigurationService;
import com.gni.frmk.tools.addon.service.ReportService;
import com.wm.app.b2b.server.ServiceException;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 11 oct. 2010
 * Time: 17:44:44
 * To change this template use File | Settings | File Templates.
 */
public class CallServiceWrapper {

    private final AdminService adminService;
    private ConfigurationService configurationService;
    private ReportService reportService;

    public CallServiceWrapper(String packageNsName, String defaultConfigName) {

        final IntegrationServerUtil utils = new IntegrationServerUtil(packageNsName);
        ServiceInvokerFactory factory = new ServiceInvokerFactory() {
            public ServiceInvokerBuilder createServiceInvokerBuilder(String serviceName) {
                return new LocalServiceInvokerBuilder(utils, serviceName);
            }
        };
        WmRootInvoker rootInvoker = new WmRootInvoker(utils, factory);
        WmRootNativeInvoker rootNativeInvoker = new WmRootNativeInvoker(utils, factory);
        WmRootJmsInvoker rootJmsInvoker = new WmRootJmsInvoker(utils, factory);
        WmArtInvoker artInvoker = new WmArtInvoker(utils, factory);
        configurationService = new ConfigurationService(utils);
        reportService = new ReportService(rootNativeInvoker, rootJmsInvoker, artInvoker);
        adminService = new AdminService(defaultConfigName,
                rootInvoker,
                rootJmsInvoker,
                artInvoker,
                rootNativeInvoker,
                configurationService,
                reportService);
    }

    public void readServerConfig(IData pipeline) throws ServiceException {
        IDataCursor curPipeline = pipeline.getCursor();
        try {
            String configurationName = IDataUtil.getString(curPipeline, "configurationName");
            String xml = configurationService.loadRawConfiguration(configurationName);
            IDataUtil.put(curPipeline, "xml", xml);
        } catch (com.gni.frmk.tools.addon.invoke.ServiceException e) {
            rethrowServiceException(e);
        } finally {
            curPipeline.destroy();
        }
    }

    public void saveServerConfig(IData pipeline) throws ServiceException {
        IDataCursor curPipeline = pipeline.getCursor();
        try {
            String configurationName = IDataUtil.getString(curPipeline, "configurationName");
            configurationService.clearConfiguration(configurationName);
            Configuration cnf = reportService.reportCurrentConfiguration(configurationName);
            configurationService.saveConfiguration(cnf);
        } catch (com.gni.frmk.tools.addon.invoke.ServiceException e) {
            rethrowServiceException(e);
        } finally {
            curPipeline.destroy();
        }
    }

    public void closeServer(IData pipeline) throws ServiceException {
        IDataCursor curPipeline = pipeline.getCursor();
        try {
            int maxSecondsToWait = IDataUtil.getInt(curPipeline, "maxSecondsToWait", 10);
            boolean saveServerConfig = IDataUtil.getBoolean(curPipeline, "saveServerConfig", false);
            adminService.closeServer(maxSecondsToWait, saveServerConfig);
        } catch (com.gni.frmk.tools.addon.invoke.ServiceException e) {
            rethrowServiceException(e);
        } finally {
            curPipeline.destroy();
        }
    }

    public void openServer(IData pipeline) throws ServiceException {
        try {
            adminService.openServer();
        } catch (com.gni.frmk.tools.addon.invoke.ServiceException e) {
            rethrowServiceException(e);
        }
    }

    public void openFullServer(IData pipeline) throws ServiceException {
        IDataCursor curPipeline = pipeline.getCursor();
        try {
            boolean saveServerConfig = IDataUtil.getBoolean(curPipeline, "saveServerConfig", false);
            adminService.openFullServer(saveServerConfig);
        } catch (com.gni.frmk.tools.addon.invoke.ServiceException e) {
            rethrowServiceException(e);
        } finally {
            curPipeline.destroy();
        }
    }

    private void rethrowServiceException(com.gni.frmk.tools.addon.invoke.ServiceException e) throws ServiceException {
        throw new ServiceException(e);
    }

}
