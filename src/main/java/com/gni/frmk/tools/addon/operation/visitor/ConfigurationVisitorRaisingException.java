package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.gni.frmk.tools.addon.configuration.components.AdapterListener;
import com.gni.frmk.tools.addon.configuration.components.AdapterNotification;
import com.gni.frmk.tools.addon.configuration.components.Component;
import com.gni.frmk.tools.addon.configuration.components.JmsAlias;
import com.gni.frmk.tools.addon.configuration.components.JmsTrigger;
import com.gni.frmk.tools.addon.configuration.components.NativeTrigger;
import com.gni.frmk.tools.addon.configuration.components.Port;
import com.gni.frmk.tools.addon.configuration.components.Scheduler;
import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitorRaisingException;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationVisitorRaisingException extends ComponentVisitorRaisingException{

    @Override
    void visit(AdapterConnection visited) throws ConfigurationVisitorException;

    @Override
    void visit(AdapterListener visited) throws ConfigurationVisitorException;

    @Override
    void visit(AdapterNotification visited) throws ConfigurationVisitorException;

    @Override
    void visit(Port visited) throws ConfigurationVisitorException;

    @Override
    void visit(Scheduler visited) throws ConfigurationVisitorException;

    @Override
    void visit(NativeTrigger visited) throws ConfigurationVisitorException;

    @Override
    void visit(JmsTrigger visited) throws ConfigurationVisitorException;

    @Override
    void visit(JmsAlias visited) throws ConfigurationVisitorException;

    public static class ConfigurationVisitorException extends ComponentVisitorException {
        //TODO ajouter configuration visited
//        private final Configuration configuration;

        public ConfigurationVisitorException(Component component, Throwable cause) {
            super(component, cause);
        }

//        public Configuration getConfiguration() {
//            return configuration;
//        }
    }
}
