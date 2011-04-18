package com.gni.frmk.tools.addon.visitor;


import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.Component;
import com.gni.frmk.tools.addon.model.component.*;

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
    public void visitComponent(ImmutableAdapterConnection visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(ImmutableAdapterListener visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(ImmutableAdapterNotification visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(ImmutablePort visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(ImmutableScheduler visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(ImmutableNativeTrigger visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(ImmutableJmsTrigger visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(ImmutableJmsAlias visited) {
        visitAny(visited);
    }

    @Override
    public void visitComponent(ImmutableIntegrationServerPackage visited) {
        visitAny(visited);
    }
}
