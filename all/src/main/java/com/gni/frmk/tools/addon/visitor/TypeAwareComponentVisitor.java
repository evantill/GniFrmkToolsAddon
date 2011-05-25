package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentVisitor;
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
 * Date: 23/05/11
 * Time: 08:56
 *
 * @author: e03229
 */
public abstract class TypeAwareComponentVisitor extends ReflectionBaseVisitor
        implements ComponentVisitor {

    private static final String COMPONENT_VISITOR_PREFIX = "Component";

    @Override
    public void visitComponent(Component<?, ?, ?> visited) {
        super.visit(visited, COMPONENT_VISITOR_PREFIX);
    }

    protected abstract void visitComponentAdapterConnection(AdapterConnection visited);

    protected abstract void visitComponentAdapterListener(AdapterListener visited);

    protected abstract void visitComponentAdapterNotification(AdapterNotification visited);

    protected abstract void visitComponentJmsAlias(JmsAlias visited);

    protected abstract void visitComponentJmsTrigger(JmsTrigger visited);

    protected abstract void visitComponentNativeTrigger(NativeTrigger visited);

    protected abstract void visitComponentPort(Port visited);

    protected abstract void visitComponentScheduler(Scheduler visited);
}
