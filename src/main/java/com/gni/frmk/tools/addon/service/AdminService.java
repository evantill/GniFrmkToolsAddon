package com.gni.frmk.tools.addon.service;

import com.gni.frmk.tools.addon.data.Configuration;
import com.gni.frmk.tools.addon.invoke.divers.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootNativeInvoker;
import com.gni.frmk.tools.addon.operation.strategy.CloseInputStrategy;
import com.gni.frmk.tools.addon.operation.strategy.CloseOutputStrategy;
import com.gni.frmk.tools.addon.operation.visitor.ApplyConfigurationVisitor;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.operation.visitor.TraceConfigurationVisitorAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 11 oct. 2010
 * Time: 16:58:58
 * To change this template use File | Settings | File Templates.
 */
public class AdminService {

    private final ConfigurationVisitor changeConfigVisitor;
    private final String defaultConfigurationName;
    private final ReportService reportSrv;
    private final ConfigurationService confSrv;
    private final WmRootInvoker rootInvoker;

    public AdminService(String defaultConfigurationName,
                        WmRootInvoker rootInvoker, WmRootJmsInvoker jmsInvoker, WmArtInvoker artInvoker,
                        WmRootNativeInvoker rootNativeInvoker,
                        ConfigurationService confSrv,
                        ReportService reportSrv) {
        this.changeConfigVisitor = new TraceConfigurationVisitorAdapter(new ApplyConfigurationVisitor(artInvoker, jmsInvoker, rootNativeInvoker));
        this.confSrv = confSrv;
        this.reportSrv = reportSrv;
        this.defaultConfigurationName = defaultConfigurationName;
        this.rootInvoker = rootInvoker;
    }

    private void changeServerInputState(Configuration cnf) throws ServiceException {
        CloseInputStrategy strategy = new CloseInputStrategy(changeConfigVisitor);
        strategy.execute(cnf);
    }

    private void changeServerOutputState(Configuration cnf) throws ServiceException {
        CloseOutputStrategy strategy = new CloseOutputStrategy(changeConfigVisitor);
        strategy.execute(cnf);
    }

    public void closeServer(long maxSecondsToWait, boolean saveServerConfig) throws ServiceException {
        Configuration defaultCnf = reportSrv.reportCurrentConfiguration(defaultConfigurationName);
        if (saveServerConfig) {
            confSrv.saveConfiguration(defaultCnf);
        }
        Configuration closeAll = confSrv.closeAllConfiguration(defaultCnf);
        changeServerInputState(closeAll);
        try {
            rootInvoker.waitServicesEnd(maxSecondsToWait);
        } finally {
            changeServerOutputState(closeAll);
        }
    }

    public void openServer() throws ServiceException {
        Configuration openCnf = confSrv.loadConfiguration(defaultConfigurationName);
        changeServerOutputState(openCnf);
        changeServerInputState(openCnf);
    }

    public void openFullServer(boolean saveServerConfig) throws ServiceException {
        Configuration cnf = reportSrv.reportCurrentConfiguration(defaultConfigurationName);
        Configuration openAll = confSrv.openAllConfiguration(cnf);
        if (saveServerConfig) {
            confSrv.saveConfiguration(openAll);
        }
        changeServerOutputState(openAll);
        changeServerInputState(openAll);
    }
}
