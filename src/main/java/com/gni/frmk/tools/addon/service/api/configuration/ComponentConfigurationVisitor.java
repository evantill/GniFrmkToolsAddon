package com.gni.frmk.tools.addon.service.api.configuration;

import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.gni.frmk.tools.addon.service.api.Visitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:15
 *
 * @author: e03229
 */
public interface ComponentConfigurationVisitor extends Visitor<ComponentConfigurationVisitor, ComponentConfigurationVisited> {
    void visit(AdapterConnectionConfiguration visited);

    void visit(AdapterListenerConfiguration visited);

    void visit(AdapterNotificationConfiguration visited);

    void visit(PortConfiguration visited);

    void visit(SchedulerConfiguration visited);

    void visit(NativeTriggerConfiguration visited);

    void visit(JmsTriggerConfiguration visited);

    void visit(JmsAliasConfiguration visited);

    void visit(IntegrationServerPackageConfiguration visited);

}
