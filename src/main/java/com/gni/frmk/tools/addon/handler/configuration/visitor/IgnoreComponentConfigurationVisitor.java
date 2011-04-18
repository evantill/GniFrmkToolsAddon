package com.gni.frmk.tools.addon.handler.configuration.visitor;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.configuration.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/04/11
 * Time: 10:13
 *
 * @author: e03229
 */
public abstract class IgnoreComponentConfigurationVisitor implements ConfigurationVisitor {
    @Override
    public final void visitComponentConfiguration(AdapterConnectionConfiguration visited) {}

    @Override
    public final void visitComponentConfiguration(AdapterListenerConfiguration visited) {}

    @Override
    public final void visitComponentConfiguration(AdapterNotificationConfiguration visited) {}

    @Override
    public final void visitComponentConfiguration(PortConfiguration visited) {}

    @Override
    public final void visitComponentConfiguration(SchedulerConfiguration visited) {}

    @Override
    public final void visitComponentConfiguration(NativeTriggerConfiguration visited) {}

    @Override
    public final void visitComponentConfiguration(JmsTriggerConfiguration visited) {}

    @Override
    public final void visitComponentConfiguration(JmsAliasConfiguration visited) {}

    @Override
    public final void visitComponentConfiguration(IntegrationServerPackageConfiguration visited) {}
}
