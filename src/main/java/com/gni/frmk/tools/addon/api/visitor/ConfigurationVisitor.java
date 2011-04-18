package com.gni.frmk.tools.addon.api.visitor;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:39
 *
 * @author: e03229
 */
public interface ConfigurationVisitor
        extends Visitor<ConfigurationVisitor, ConfigurationVisited> {

    void visitComponent(AdapterConnection visited);

    void visitComponent(AdapterListener visited);

    void visitComponent(AdapterNotification visited);

    void visitComponent(Port visited);

    void visitComponent(Scheduler visited);

    void visitComponent(NativeTrigger visited);

    void visitComponent(JmsTrigger visited);

    void visitComponent(JmsAlias visited);

    void visitComponent(IntegrationServerPackage visited);

    void visitConfiguration(Configuration configuration);

    void visitComponentConfiguration(AdapterConnectionConfiguration visited);

    void visitComponentConfiguration(AdapterListenerConfiguration visited);

    void visitComponentConfiguration(AdapterNotificationConfiguration visited);

    void visitComponentConfiguration(PortConfiguration visited);

    void visitComponentConfiguration(SchedulerConfiguration visited);

    void visitComponentConfiguration(NativeTriggerConfiguration visited);

    void visitComponentConfiguration(JmsTriggerConfiguration visited);

    void visitComponentConfiguration(JmsAliasConfiguration visited);

    void visitComponentConfiguration(IntegrationServerPackageConfiguration visited);
}
