package com.gni.frmk.tools.addon.oldies.services;

import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.wm.app.b2b.server.InvokeException;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 11 oct. 2010
 * Time: 16:58:58
 * To change this template use File | Settings | File Templates.
 */
public class AdminService {

//    private final ConfigurationVisitor changeConfigVisitor;
    private final String defaultConfigurationName;
    private final ReportService reportSrv;
    private final ConfigurationService confSrv;

    public AdminService(String defaultConfigurationName,
            Object rootInvoker, Object jmsInvoker, Object artInvoker,
            ConfigurationService confSrv,
            ReportService reportSrv) {
//        this.changeConfigVisitor = new TraceConfigurationVisitorAdapter(new ApplyConfigurationVisitor(artInvoker, jmsInvoker, rootInvoker));
        this.confSrv = confSrv;
        this.reportSrv = reportSrv;
        this.defaultConfigurationName = defaultConfigurationName;
    }

    private void changeServerInputState(Configuration cnf) {
//        CloseInputStrategy strategy = new CloseInputStrategy(changeConfigVisitor);
//        strategy.execute(cnf);
    }

    private void changeServerOutputState(Configuration cnf) {
//        CloseOutputStrategy strategy = new CloseOutputStrategy(changeConfigVisitor);
//        strategy.execute(cnf);
    }

    public void closeServer(long maxSecondsToWait, boolean saveServerConfig) throws InvokeException, ActionException {
        //Configuration defaultCnf = reportSrv.reportCurrentConfiguration(defaultConfigurationName);
//        if (saveServerConfig) {
//            confSrv.saveConfiguration(defaultCnf);
//        }
//        Configuration closeAll = confSrv.closeAllConfiguration(defaultCnf);
//        changeServerInputState(closeAll);
//        try {
            //rootInvoker.waitServicesEnd(maxSecondsToWait);
//        } finally {
//            changeServerOutputState(closeAll);
//        }
    }

    public void openServer() {
//        ImmutableConfiguration openCnf = confSrv.loadConfiguration(defaultConfigurationName);
//        changeServerOutputState(openCnf);
//        changeServerInputState(openCnf);
    }

    public void openFullServer(boolean saveServerConfig) throws InvokeException, ActionException {
//        ImmutableConfiguration cnf = reportSrv.reportCurrentConfiguration(defaultConfigurationName);
//        ImmutableConfiguration openAll = confSrv.openAllConfiguration(cnf);
//        if (saveServerConfig) {
//            confSrv.saveConfiguration(openAll);
//        }
//        changeServerOutputState(openAll);
//        changeServerInputState(openAll);
    }
}
