package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration.MutableConfiguration;

import static com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus.INACTIVE;
import static com.gni.frmk.tools.addon.model.component.state.ConnectableState.ConnectableStatus.DISCONNECTED;
import static com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus.DISABLED;
import static com.gni.frmk.tools.addon.model.component.state.SchedulerState.SchedulerStatus.EXPIRED;
import static com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState.TemporaryStatus.PERMANENT;


/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class DisableStatusVisitor /*implements UpdateConfigurationVisitor*/ {

    MutableConfiguration builder = ImmutableConfiguration.builder();

    public void visit(ImmutableAdapterConnection visited) {
        ImmutableAdapterConnection changed = ImmutableAdapterConnection.builder()
                                                     .from(visited)
                                                     .defineState(new EnableState(DISABLED))
                                                     .build();
//        builder.addAdapterConnection(changed);
    }

    public void visit(ImmutableAdapterListener visited) {
        ImmutableAdapterListener changed = ImmutableAdapterListener.builder()
                                                 .from(visited)
                                                 .defineState(new ActivableState(DISABLED, INACTIVE))
                                                 .build();
//        builder.addAdapterListener(changed);
    }

    public void visit(ImmutableAdapterNotification visited) {
        ImmutableAdapterNotification changed = ImmutableAdapterNotification.builder()
                                                         .from(visited)
                                                         .defineState(new ActivableState(DISABLED, INACTIVE))
                                                         .build();
//        builder.addAdapterNotification(changed);
    }

    public void visit(ImmutablePort visited) {
        ImmutablePort changed = ImmutablePort.builder()
                           .from(visited)
                           .defineState(new ActivableState(DISABLED, INACTIVE))
                           .build();
//        builder.addPort(changed);
    }

    public void visit(ImmutableScheduler visited) {
        ImmutableScheduler changed = ImmutableScheduler.builder()
                                     .from(visited)
                                     .defineState(new SchedulerState(DISABLED, EXPIRED))
                                     .build();
//        builder.addScheduler(changed);
    }

    public void visit(ImmutableJmsAlias visited) {
        ImmutableJmsAlias changed = ImmutableJmsAlias.builder()
                                   .from(visited)
                                   .defineState(new ConnectableState(DISABLED, DISCONNECTED))
                                   .build();
//        builder.addJmsAliasConnection(changed);
    }

    public void visit(ImmutableJmsTrigger visited) {
        ImmutableJmsTrigger changed = ImmutableJmsTrigger.builder()
                                       .from(visited)
                                       .defineState(new ActivableState(DISABLED, INACTIVE))
                                       .build();
//        builder.addJmsTrigger(changed);
    }

    public void visit(ImmutableNativeTrigger visited) {
        NativeTriggerState changedState = NativeTriggerState.builder()
                                                            .defineEnable(DISABLED)
                                                            .defineProcessing(PERMANENT, INACTIVE)
                                                            .defineRetrieval(PERMANENT, INACTIVE)
                                                            .build();
        ImmutableNativeTrigger changed = ImmutableNativeTrigger.builder()
                                             .from(visited)
                                             .defineState(changedState)
                                             .build();
//        builder.addNativeTrigger(changed);
    }

    //@Override
    public void visit(ImmutableIntegrationServerPackage visited) {
        ImmutableIntegrationServerPackage changedState = ImmutableIntegrationServerPackage.builder()
                                                                        .from(visited)
                                                                        .defineState(new EnableState(DISABLED))
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
