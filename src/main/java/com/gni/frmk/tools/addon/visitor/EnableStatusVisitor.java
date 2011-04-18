package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration.MutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.component.ComponentConfiguration;

import static com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus.ACTIVE;
import static com.gni.frmk.tools.addon.model.component.state.ConnectableState.ConnectableStatus.CONNECTED;
import static com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus.ENABLED;
import static com.gni.frmk.tools.addon.model.component.state.SchedulerState.SchedulerStatus.UNEXPIRED;
import static com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState.TemporaryStatus.PERMANENT;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
public class EnableStatusVisitor /*implements UpdateConfigurationVisitor*/ {

    MutableConfiguration builder = ImmutableConfiguration.builder();

//    @Override
    public <C extends AbstractComponent<?, S>, S extends AbstractComponentState> void visitComponentConfiguration(ComponentConfiguration<C, S> visited) {
//        visited.getComponent().setState(visited.getStates().get(ComponentStateContext.OPEN));
    }

    public void visit(ImmutableAdapterConnection visited) {
        ImmutableAdapterConnection changed = ImmutableAdapterConnection.builder()
                                                     .from(visited)
                                                     .defineState(new EnableState(ENABLED))
                                                     .build();
//        builder.addAdapterConnection(changed);
    }

    public void visit(ImmutableAdapterListener visited) {
        ImmutableAdapterListener changed = ImmutableAdapterListener.builder()
                                                 .from(visited)
                                                 .defineState(new ActivableState(ENABLED, ACTIVE))
                                                 .build();
//        builder.addAdapterListener(changed);
    }

    public void visit(ImmutableAdapterNotification visited) {
        ImmutableAdapterNotification changed = ImmutableAdapterNotification.builder()
                                                         .from(visited)
                                                         .defineState(new ActivableState(ENABLED, ACTIVE))
                                                         .build();
//        builder.addAdapterNotification(changed);
    }

    public void visit(ImmutablePort visited) {
        ImmutablePort changed = ImmutablePort.builder()
                           .from(visited)
                           .defineState(new ActivableState(ENABLED, ACTIVE))
                           .build();
//        builder.addPort(changed);
    }

    public void visit(ImmutableScheduler visited) {
        ImmutableScheduler changed = ImmutableScheduler.builder()
                                     .from(visited)
                                     .defineState(new SchedulerState(ENABLED, UNEXPIRED))
                                     .build();
//        builder.addScheduler(changed);
    }

    public void visit(ImmutableJmsAlias visited) {
        ImmutableJmsAlias changed = ImmutableJmsAlias.builder()
                                   .from(visited)
                                   .defineState(new ConnectableState(ENABLED, CONNECTED))
                                   .build();
//        builder.addJmsAliasConnection(changed);
    }

    public void visit(ImmutableJmsTrigger visited) {
        ImmutableJmsTrigger changed = ImmutableJmsTrigger.builder()
                                       .from(visited)
                                       .defineState(new ActivableState(ENABLED, ACTIVE))
                                       .build();
//        builder.addJmsTrigger(changed);
    }

    public void visit(ImmutableNativeTrigger visited) {
        NativeTriggerState changedState = NativeTriggerState.builder()
                                                            .defineEnable(ENABLED)
                                                            .defineProcessing(PERMANENT, ACTIVE)
                                                            .defineRetrieval(PERMANENT, ACTIVE)
                                                            .build();
        ImmutableNativeTrigger changed = ImmutableNativeTrigger.builder()
                                             .from(visited)
                                             .defineState(changedState)
                                             .build();
//        builder.addNativeTrigger(changed);
    }

//    @Override
    public void visit(ImmutableIntegrationServerPackage visited) {
        ImmutableIntegrationServerPackage changedState = ImmutableIntegrationServerPackage.builder()
                                                                        .from(visited)
                                                                        .defineState(new EnableState(ENABLED))
                                                                        .build();
//        builder.addPackage(changedState);
    }

    public void clear() {
        builder.clear();
    }

    public ImmutableConfiguration getUpdatedConfiguration() {
        return builder.build();
    }
}

