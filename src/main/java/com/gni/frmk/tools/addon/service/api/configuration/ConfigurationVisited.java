package com.gni.frmk.tools.addon.service.api.configuration;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.component.*;
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
    List<AdapterConnectionConfiguration> getAdapterConnectionConfigurations();

    List<AdapterListenerConfiguration> getAdapterListenerConfigurations();

    List<AdapterNotificationConfiguration> getAdapterNotificationConfigurations();

    List<IntegrationServerPackageConfiguration> getIntegrationServerPackageConfigurations();

    List<JmsAliasConfiguration> getJmsAliasConfigurations();

    List<JmsTriggerConfiguration> getJmsTriggerConfigurations();

    List<NativeTriggerConfiguration> getNativeTriggerConfigurations();

    List<PortConfiguration> getPortConfigurations();

    List<SchedulerConfiguration> getSchedulerConfigurations();

}
