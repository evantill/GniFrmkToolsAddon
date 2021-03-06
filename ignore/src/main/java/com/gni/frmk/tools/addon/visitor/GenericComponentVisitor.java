package com.gni.frmk.tools.addon.visitor;


import com.gni.frmk.tools.addon.api.custom.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.*;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericComponentVisitor implements ConfigurationVisitor {

    public abstract void visitAny(Component visited);

    @Override
    public void visitComponent(AdapterConnection visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(AdapterListener visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(AdapterNotification visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(Port visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(Scheduler visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(NativeTrigger visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(JmsTrigger visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(JmsAlias visited) {
        visitAny(visited);
    }
}
