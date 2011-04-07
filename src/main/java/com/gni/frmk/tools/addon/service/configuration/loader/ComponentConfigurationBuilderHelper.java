package com.gni.frmk.tools.addon.service.configuration.loader;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisited;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisitor;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;

import static com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus.ACTIVE;
import static com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus.INACTIVE;
import static com.gni.frmk.tools.addon.model.component.state.ConnectableState.ConnectableStatus.CONNECTED;
import static com.gni.frmk.tools.addon.model.component.state.ConnectableState.ConnectableStatus.DISCONNECTED;
import static com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus.DISABLED;
import static com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus.ENABLED;
import static com.gni.frmk.tools.addon.model.component.state.SchedulerState.SchedulerStatus.EXPIRED;
import static com.gni.frmk.tools.addon.model.component.state.SchedulerState.SchedulerStatus.UNEXPIRED;
import static com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState.TemporaryStatus.PERMANENT;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 18:42
 *
 * @author: e03229
 */
class ComponentConfigurationBuilderHelper implements ComponentVisitor {

    private final ComponentConfigurationVisitor visitor;

    public ComponentConfigurationBuilderHelper(ComponentConfigurationVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void dispatchVisit(ComponentVisited visitable) {
        visitable.accept(this);
    }

    @Override
    public void visit(AdapterConnection visited) {
        AdapterConnectionConfiguration.Builder builder = AdapterConnectionConfiguration.builder();
        AdapterConnectionConfiguration conf = builder.defineComponent(visited)
                                                     .exist(true)
                                                     .select(true)
                                                     .defineCloseState(new EnableState(DISABLED))
                                                     .defineOpenState(new EnableState(ENABLED))
                                                     .build();
        visitor.visit(conf);
    }

    @Override
    public void visit(AdapterListener visited) {
        AdapterListenerConfiguration.Builder builder = AdapterListenerConfiguration.builder();
        AdapterListenerConfiguration cnf = builder.defineComponent(visited)
                                                  .exist(true)
                                                  .select(true)
                                                  .defineCloseState(new ActivableState(DISABLED, INACTIVE))
                                                  .defineOpenState(new ActivableState(ENABLED, ACTIVE))
                                                  .build();
        visitor.visit(cnf);
    }

    @Override
    public void visit(AdapterNotification visited) {
        AdapterNotificationConfiguration.Builder builder = AdapterNotificationConfiguration.builder();
        AdapterNotificationConfiguration cnf = builder.defineComponent(visited)
                                                      .exist(true)
                                                      .select(true)
                                                      .defineCloseState(new ActivableState(DISABLED, INACTIVE))
                                                      .defineOpenState(new ActivableState(ENABLED, ACTIVE))
                                                      .build();
        visitor.visit(cnf);
    }

    @Override
    public void visit(Port visited) {
        PortConfiguration.Builder builder = PortConfiguration.builder();
        PortConfiguration cnf = builder.defineComponent(visited)
                                       .exist(true)
                                       .select(true)
                                       .defineCloseState(new ActivableState(DISABLED, INACTIVE))
                                       .defineOpenState(new ActivableState(ENABLED, ACTIVE))
                                       .build();
        visitor.visit(cnf);
    }

    @Override
    public void visit(Scheduler visited) {
        SchedulerConfiguration.Builder builder = SchedulerConfiguration.builder();
        SchedulerConfiguration cnf = builder.defineComponent(visited)
                                            .exist(true)
                                            .select(true)
                                            .defineCloseState(new SchedulerState(DISABLED, EXPIRED))
                                            .defineOpenState(new SchedulerState(ENABLED, UNEXPIRED))
                                            .build();
        visitor.visit(cnf);
    }

    @Override
    public void visit(NativeTrigger visited) {
        NativeTriggerConfiguration.Builder builder = NativeTriggerConfiguration.builder();
        NativeTriggerState closeState = NativeTriggerState.builder()
                                                          .defineEnable(DISABLED)
                                                          .defineProcessing(PERMANENT, INACTIVE)
                                                          .defineRetrieval(PERMANENT, INACTIVE)
                                                          .build();
        NativeTriggerState openState = NativeTriggerState.builder()
                                                         .defineEnable(ENABLED)
                                                         .defineProcessing(PERMANENT, ACTIVE)
                                                         .defineRetrieval(PERMANENT, ACTIVE)
                                                         .build();
        NativeTriggerConfiguration cnf = builder.defineComponent(visited)
                                                .exist(true)
                                                .select(true)
                                                .defineCloseState(closeState)
                                                .defineOpenState(openState)
                                                .build();
        visitor.visit(cnf);
    }

    @Override
    public void visit(JmsTrigger visited) {
        JmsTriggerConfiguration.Builder builder = JmsTriggerConfiguration.builder();
        JmsTriggerConfiguration cnf = builder.defineComponent(visited)
                                             .exist(true)
                                             .select(true)
                                             .defineCloseState(new ActivableState(DISABLED, INACTIVE))
                                             .defineOpenState(new ActivableState(ENABLED, ACTIVE))
                                             .build();
        visitor.visit(cnf);
    }

    @Override
    public void visit(JmsAlias visited) {
        JmsAliasConfiguration.Builder builder = JmsAliasConfiguration.builder();
        JmsAliasConfiguration cnf = builder.defineComponent(visited)
                                           .exist(true)
                                           .select(true)
                                           .defineCloseState(new ConnectableState(DISABLED, DISCONNECTED))
                                           .defineOpenState(new ConnectableState(ENABLED, CONNECTED))
                                           .build();
        visitor.visit(cnf);
    }

    @Override
    public void visit(IntegrationServerPackage visited) {
        IntegrationServerPackageConfiguration.Builder builder = IntegrationServerPackageConfiguration.builder();
        IntegrationServerPackageConfiguration cnf = builder.defineComponent(visited)
                                                           .exist(true)
                                                           .select(true)
                                                           .defineCloseState(new EnableState(DISABLED))
                                                           .defineOpenState(new EnableState(ENABLED))
                                                           .build();
        visitor.visit(cnf);
    }
}
