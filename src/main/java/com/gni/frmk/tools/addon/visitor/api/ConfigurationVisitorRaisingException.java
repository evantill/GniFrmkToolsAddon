package com.gni.frmk.tools.addon.visitor.api;

import com.gni.frmk.tools.addon.model.api.Component;
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
    void visit(ImmutableAdapterConnection visited) throws ConfigurationVisitorException;

    @Override
    void visit(ImmutableAdapterListener visited) throws ConfigurationVisitorException;;

    @Override
    void visit(ImmutableAdapterNotification visited) throws ConfigurationVisitorException;;

    @Override
    void visit(ImmutablePort visited) throws ConfigurationVisitorException;;

    @Override
    void visit(ImmutableScheduler visited) throws ConfigurationVisitorException;

    @Override
    void visit(ImmutableNativeTrigger visited) throws ConfigurationVisitorException;

    @Override
    void visit(ImmutableJmsTrigger visited) throws ConfigurationVisitorException;;

    @Override
    void visit(ImmutableJmsAlias visited) throws ConfigurationVisitorException;;


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
