package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.Configuration.Builder;

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

    Builder builder = Configuration.builder();

    public void visit(AdapterConnection visited) {
        AdapterConnection changed = AdapterConnection.builder()
                                                     .from(visited)
                                                     .defineState(new EnableState(DISABLED))
                                                     .build();
//        builder.addAdapterConnection(changed);
    }

    public void visit(AdapterListener visited) {
        AdapterListener changed = AdapterListener.builder()
                                                 .from(visited)
                                                 .defineState(new ActivableState(DISABLED, INACTIVE))
                                                 .build();
//        builder.addAdapterListener(changed);
    }

    public void visit(AdapterNotification visited) {
        AdapterNotification changed = AdapterNotification.builder()
                                                         .from(visited)
                                                         .defineState(new ActivableState(DISABLED, INACTIVE))
                                                         .build();
//        builder.addAdapterNotification(changed);
    }

    public void visit(Port visited) {
        Port changed = Port.builder()
                           .from(visited)
                           .defineState(new ActivableState(DISABLED, INACTIVE))
                           .build();
//        builder.addPort(changed);
    }

    public void visit(Scheduler visited) {
        Scheduler changed = Scheduler.builder()
                                     .from(visited)
                                     .defineState(new SchedulerState(DISABLED, EXPIRED))
                                     .build();
//        builder.addScheduler(changed);
    }

    public void visit(JmsAlias visited) {
        JmsAlias changed = JmsAlias.builder()
                                   .from(visited)
                                   .defineState(new ConnectableState(DISABLED, DISCONNECTED))
                                   .build();
//        builder.addJmsAliasConnection(changed);
    }

    public void visit(JmsTrigger visited) {
        JmsTrigger changed = JmsTrigger.builder()
                                       .from(visited)
                                       .defineState(new ActivableState(DISABLED, INACTIVE))
                                       .build();
//        builder.addJmsTrigger(changed);
    }

    public void visit(NativeTrigger visited) {
        NativeTriggerState changedState = NativeTriggerState.builder()
                                                            .defineEnable(DISABLED)
                                                            .defineProcessing(PERMANENT, INACTIVE)
                                                            .defineRetrieval(PERMANENT, INACTIVE)
                                                            .build();
        NativeTrigger changed = NativeTrigger.builder()
                                             .from(visited)
                                             .defineState(changedState)
                                             .build();
//        builder.addNativeTrigger(changed);
    }

    //@Override
    public void visit(IntegrationServerPackage visited) {
        IntegrationServerPackage changedState = IntegrationServerPackage.builder()
                                                                        .from(visited)
                                                                        .defineState(new EnableState(DISABLED))
                                                                        .build();
//        builder.addPackage(changedState);
    }

    public void clear() {
        builder.clear();
    }

    public Configuration getUpdatedConfiguration() {
        return builder.build();
    }
}