package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.Configuration;
import com.gni.frmk.tools.addon.configuration.Configuration.Builder;
import com.gni.frmk.tools.addon.configuration.components.ActivableState;
import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.gni.frmk.tools.addon.configuration.components.AdapterListener;
import com.gni.frmk.tools.addon.configuration.components.AdapterNotification;
import com.gni.frmk.tools.addon.configuration.components.ConnectableState;
import com.gni.frmk.tools.addon.configuration.components.EnableState;
import com.gni.frmk.tools.addon.configuration.components.JmsAlias;
import com.gni.frmk.tools.addon.configuration.components.JmsTrigger;
import com.gni.frmk.tools.addon.configuration.components.NativeTrigger;
import com.gni.frmk.tools.addon.configuration.components.NativeTrigger.NativeTriggerState;
import com.gni.frmk.tools.addon.configuration.components.Port;
import com.gni.frmk.tools.addon.configuration.components.Scheduler;
import com.gni.frmk.tools.addon.configuration.components.Scheduler.SchedulerState;

import static com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus.ACTIVE;
import static com.gni.frmk.tools.addon.configuration.components.ConnectableState.ConnectableStatus.CONNECTED;
import static com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus.ENABLED;
import static com.gni.frmk.tools.addon.configuration.components.Scheduler.SchedulerState.SchedulerStatus.UNEXPIRED;
import static com.gni.frmk.tools.addon.configuration.components.TemporaryActivableState.TemporaryStatus.PERMANENT;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
public class EnableStatusVisitor implements UpdateConfigurationVisitor {

    Builder builder = Configuration.builder();

    public void visit(AdapterConnection visited) {
        AdapterConnection changed = AdapterConnection.builder()
                                                     .from(visited)
                                                     .defineState(new EnableState(ENABLED))
                                                     .build();
        builder.addAdapterConnection(changed);
    }

    public void visit(AdapterListener visited) {
        AdapterListener changed = AdapterListener.builder()
                                                 .from(visited)
                                                 .defineState(new ActivableState(ENABLED, ACTIVE))
                                                 .build();
        builder.addAdapterListener(changed);
    }

    public void visit(AdapterNotification visited) {
        AdapterNotification changed = AdapterNotification.builder()
                                                         .from(visited)
                                                         .defineState(new ActivableState(ENABLED, ACTIVE))
                                                         .build();
        builder.addAdapterNotification(changed);
    }

    public void visit(Port visited) {
        Port changed = Port.builder()
                           .from(visited)
                           .defineState(new ActivableState(ENABLED, ACTIVE))
                           .build();
        builder.addPort(changed);
    }

    public void visit(Scheduler visited) {
        Scheduler changed = Scheduler.builder()
                                     .from(visited)
                                     .defineState(new SchedulerState(ENABLED, UNEXPIRED))
                                     .build();
        builder.addScheduler(changed);
    }

    public void visit(JmsAlias visited) {
        JmsAlias changed = JmsAlias.builder()
                                   .from(visited)
                                   .defineState(new ConnectableState(ENABLED, CONNECTED))
                                   .build();
        builder.addJmsAliasConnection(changed);
    }

    public void visit(JmsTrigger visited) {
        JmsTrigger changed = JmsTrigger.builder()
                                       .from(visited)
                                       .defineState(new ActivableState(ENABLED, ACTIVE))
                                       .build();
        builder.addJmsTrigger(changed);
    }

    public void visit(NativeTrigger visited) {
        NativeTriggerState changedState = NativeTriggerState.builder()
                                                            .defineEnable(ENABLED)
                                                            .defineProcessing(PERMANENT, ACTIVE)
                                                            .defineRetrieval(PERMANENT, ACTIVE)
                                                            .build();
        NativeTrigger changed = NativeTrigger.builder()
                                             .from(visited)
                                             .defineState(changedState)
                                             .build();
        builder.addNativeTrigger(changed);
    }

    public void clear() {
        builder.clear();
    }

    public Configuration getUpdatedConfiguration() {
        return builder.buildAndValidate();
    }
}

