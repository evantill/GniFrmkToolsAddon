package com.gni.frmk.tools.addon.visitor.api;

import com.gni.frmk.tools.addon.model.*;
import com.gni.frmk.tools.addon.model.component.*;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationVisitorRaisingException extends ComponentVisitorRaisingException {

    @Override
    void visit(AdapterConnection visited) throws ConfigurationVisitorException;

    @Override
    void visit(AdapterListener visited) throws ConfigurationVisitorException;

    ;

    @Override
    void visit(AdapterNotification visited) throws ConfigurationVisitorException;

    ;

    @Override
    void visit(Port visited) throws ConfigurationVisitorException;

    ;

    @Override
    void visit(Scheduler visited) throws ConfigurationVisitorException;

    @Override
    void visit(NativeTrigger visited) throws ConfigurationVisitorException;

    @Override
    void visit(JmsTrigger visited) throws ConfigurationVisitorException;

    ;

    @Override
    void visit(JmsAlias visited) throws ConfigurationVisitorException;

    ;


    /**
     * Created by IntelliJ IDEA.
     * User: e03229
     * Date: 03/11/10
     * Time: 15:44
     * To change this template use File | Settings | File Templates.
     */
    class ConfigurationVisitorException extends ComponentVisitorException {

        //TODO ajouter la configuration

        public ConfigurationVisitorException(Component component, Throwable caught) {
            super(component, caught);
        }

        public ConfigurationVisitorException(Component component, String message) {
            super(component, message);
        }

    }

}
