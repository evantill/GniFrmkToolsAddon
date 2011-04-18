package com.gni.frmk.tools.addon.handler.configuration.visitor;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/04/11
 * Time: 10:13
 *
 * @author: e03229
 */
public abstract class IgnoreComponentVisitor implements ConfigurationVisitor {
    @Override
    public final void visitComponent(ImmutableAdapterConnection visited) {}

    @Override
    public final void visitComponent(ImmutableAdapterListener visited) {}

    @Override
    public final void visitComponent(ImmutableAdapterNotification visited) {}

    @Override
    public final void visitComponent(ImmutablePort visited) {}

    @Override
    public final void visitComponent(ImmutableScheduler visited) {}

    @Override
    public final void visitComponent(ImmutableNativeTrigger visited) {}

    @Override
    public final void visitComponent(ImmutableJmsTrigger visited) {}

    @Override
    public final void visitComponent(ImmutableJmsAlias visited) {}

    @Override
    public final void visitComponent(ImmutableIntegrationServerPackage visited) {}
}
