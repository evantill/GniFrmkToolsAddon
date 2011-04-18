package com.gni.frmk.tools.addon;

import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.context.InvokeContextLocal;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.oldies.services.AdminService;
import com.gni.frmk.tools.addon.oldies.services.ConfigurationService;
import com.gni.frmk.tools.addon.oldies.services.ReportService;
import com.wm.app.b2b.server.ServiceException;
import com.wm.data.*;

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
        //TODO remplacer le ctx par une factory
        InvokeContext ctx = new InvokeContextLocal();
        final IntegrationServerUtil utils = new IntegrationServerUtil(packageNsName);
        WmRootInvoker rootInvoker = new WmRootInvoker(utils, ctx, packageNsName);
        WmRootJmsInvoker rootJmsInvoker = new WmRootJmsInvoker(utils, ctx, packageNsName);
        WmArtInvoker artInvoker = new WmArtInvoker(utils, ctx);
        configurationService = new ConfigurationService(utils);
        reportService = new ReportService(rootInvoker, rootJmsInvoker, artInvoker);
        adminService = new AdminService(defaultConfigName, rootInvoker, rootJmsInvoker, artInvoker, configurationService, reportService);
    }

    public void readServerConfig(IData pipeline) throws ServiceException {
        IDataCursor curPipeline = pipeline.getCursor();
        try {
            String configurationName = IDataUtil.getString(curPipeline, "configurationName");
            String xml = configurationService.loadRawConfiguration(configurationName);
            IDataUtil.put(curPipeline, "xml", xml);
        } finally {
            curPipeline.destroy();
        }
    }

    public void saveServerConfig(IData pipeline) throws ServiceException {
        IDataCursor curPipeline = pipeline.getCursor();
        try {
            String configurationName = IDataUtil.getString(curPipeline, "configurationName");
            configurationService.clearConfiguration(configurationName);
            ImmutableConfiguration cnf = reportService.reportCurrentConfiguration(configurationName);
            configurationService.saveConfiguration(cnf);
        } catch (DispatchException e) {
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
        } catch (DispatchException e) {
            rethrowServiceException(e);
        } finally {
            curPipeline.destroy();
        }
    }

    public void openServer(IData pipeline) throws ServiceException {
        adminService.openServer();
    }

    public void openFullServer(IData pipeline) throws ServiceException {
        IDataCursor curPipeline = pipeline.getCursor();
        try {
            boolean saveServerConfig = IDataUtil.getBoolean(curPipeline, "saveServerConfig", false);
            adminService.openFullServer(saveServerConfig);
        } catch (DispatchException e) {
            rethrowServiceException(e);
        } finally {
            curPipeline.destroy();
        }
    }

    private void rethrowServiceException(DispatchException e) throws ServiceException {
        throw new ServiceException(e);
    }

}
