package com.gni.frmk.tools.addon.handler.configuration.visitor;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/04/11
 * Time: 10:13
 *
 * @author: e03229
 */
public abstract class IgnoreComponentVisitor implements ConfigurationVisitor {
    @Override
    public final void visitComponent(AdapterConnection visited) {}

    @Override
    public final void visitComponent(AdapterListener visited) {}

    @Override
    public final void visitComponent(AdapterNotification visited) {}

    @Override
    public final void visitComponent(Port visited) {}

    @Override
    public final void visitComponent(Scheduler visited) {}

    @Override
    public final void visitComponent(NativeTrigger visited) {}

    @Override
    public final void visitComponent(JmsTrigger visited) {}

    @Override
    public final void visitComponent(JmsAlias visited) {}

    @Override
    public final void visitComponent(IntegrationServerPackage visited) {}
}
