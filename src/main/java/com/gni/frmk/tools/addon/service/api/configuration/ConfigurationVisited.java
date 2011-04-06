package com.gni.frmk.tools.addon.service.api.configuration;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.service.api.Visitable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:39
 *
 * @author: e03229
 */
public interface ConfigurationVisited extends Visitable<ConfigurationVisitor, ConfigurationVisited> {
    List<ComponentConfiguration<AdapterConnection, EnableState>> getAdapterConnectionConfigurations();

    List<ComponentConfiguration<AdapterListener, ActivableState>> getAdapterListenerConfigurations();

    List<ComponentConfiguration<AdapterNotification, ActivableState>> getAdapterNotificationConfigurations();

    List<ComponentConfiguration<IntegrationServerPackage, EnableState>> getIntegrationServerPackageConfigurations();

    List<ComponentConfiguration<JmsAlias, ConnectableState>> getJmsAliasConfigurations();

    List<ComponentConfiguration<JmsTrigger, ActivableState>> getJmsTriggerConfigurations();

    List<ComponentConfiguration<NativeTrigger, NativeTriggerState>> getNativeTriggerConfigurations();

    List<ComponentConfiguration<Port, ActivableState>> getPortConfigurations();

    List<ComponentConfiguration<Scheduler, SchedulerState>> getSchedulerConfigurations();

}
