package com.gni.frmk.tools.addon.visitor.api;

import com.gni.frmk.tools.addon.model.api.Component;
import com.gni.frmk.tools.addon.model.component.*;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
public interface ComponentVisitorRaisingException {

    void visit(ImmutableAdapterConnection visited) throws ComponentVisitorException;

    void visit(ImmutableAdapterListener visited) throws ComponentVisitorException;

    void visit(ImmutableAdapterNotification visited) throws ComponentVisitorException;

    void visit(ImmutablePort visited) throws ComponentVisitorException;

    void visit(ImmutableScheduler visited) throws ComponentVisitorException;

    void visit(ImmutableNativeTrigger visited) throws ComponentVisitorException;

    void visit(ImmutableJmsTrigger visited) throws ComponentVisitorException;

    void visit(ImmutableJmsAlias visited) throws ComponentVisitorException;

    void visit(ImmutableIntegrationServerPackage visited) throws ComponentVisitorException;

    public static class ComponentVisitorException extends Exception {
        private final Component component;

        public ComponentVisitorException(Component component, Throwable cause) {
            super(cause);
            this.component = component;
        }

        public ComponentVisitorException(Component component, String message) {
            super(message);
            this.component = component;
        }

        public Component getComponent() {
            return component;
        }
    }
}
