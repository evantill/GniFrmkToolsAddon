package com.gni.frmk.tools.addon.service.configuration.loader;

import com.gni.frmk.tools.addon.command.action.wm.art.RetrieveAdapterTypesList;
import com.gni.frmk.tools.addon.command.action.wm.art.connection.ListAdaptersConnections;
import com.gni.frmk.tools.addon.command.action.wm.art.listener.ListListeners;
import com.gni.frmk.tools.addon.command.action.wm.art.notifications.ListNotifications;
import com.gni.frmk.tools.addon.command.action.wm.jms.alias.GetJmsAliasReport;
import com.gni.frmk.tools.addon.command.action.wm.jms.trigger.GetJmsTriggerReport;
import com.gni.frmk.tools.addon.command.action.wm.root.ispackage.PackageList;
import com.gni.frmk.tools.addon.command.action.wm.root.port.ListPortListeners;
import com.gni.frmk.tools.addon.command.action.wm.root.scheduler.GetUserTaskList;
import com.gni.frmk.tools.addon.command.action.wm.root.trigger.GetNativeTriggerReport;
import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.ActionException;
import com.gni.frmk.tools.addon.command.api.Result;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeDispatcher;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.Configuration.Builder;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 18:28
 *
 * @author: e03229
 */
public class LoadPlateformConfiguration {

    private final Builder builder = Configuration.builder();
    private final ConfigurationBuilderHelper configurationHelper = new ConfigurationBuilderHelper(builder);
    private final ComponentConfigurationBuilderHelper componentHelper = new ComponentConfigurationBuilderHelper(configurationHelper);

    private final InvokeDispatcher dispatcher;
    private final String configurationPackageName;
    private final String configurationId;
    private final Date configurationCreationDate;

    public LoadPlateformConfiguration(InvokeDispatcher dispatcher,
            String configurationPackageName,
            String configurationId,
            Date configurationCreationDate) {
        this.dispatcher = dispatcher;
        this.configurationPackageName = configurationPackageName;
        this.configurationId = configurationId;
        this.configurationCreationDate = configurationCreationDate;
    }

    public Configuration execute() throws ActionException {
        initConfigurationHeader();
        processArtComponents();
        processRootComponents();
        processJmsComponents();
        return builder.build();
    }

    private void initConfigurationHeader() {
        builder.create(configurationPackageName, configurationId, configurationCreationDate);
    }

    private void processJmsComponents() throws ActionException {
        List<JmsAlias> aliases = dispatch(new GetJmsAliasReport()).getCollection();
        for (JmsAlias alias : aliases) {
            componentHelper.dispatchVisit(alias);
        }
        List<JmsTrigger> triggers = dispatch(new GetJmsTriggerReport()).getCollection();
        for (JmsTrigger trigger : triggers) {
            componentHelper.dispatchVisit(trigger);
        }
    }

    private void processRootComponents() throws ActionException {
        Set<IntegrationServerPackage> packages = dispatch(new PackageList()).getCollection();
        for (IntegrationServerPackage pkg : packages) {
            componentHelper.dispatchVisit(pkg);
        }
        List<Port> ports = dispatch(new ListPortListeners()).getCollection();
        for (Port port : ports) {
            componentHelper.dispatchVisit(port);
        }
        List<Scheduler> schedulers = dispatch(new GetUserTaskList()).getCollection();
        for (Scheduler scheduler : schedulers) {
            componentHelper.dispatchVisit(scheduler);
        }
        List<NativeTrigger> triggers = dispatch(new GetNativeTriggerReport()).getCollection();
        for (NativeTrigger trigger : triggers) {
            componentHelper.dispatchVisit(trigger);
        }
    }

    private void processArtComponents() throws ActionException {
        Set<String> adapterTypes = dispatch(new RetrieveAdapterTypesList()).getCollection();
        for (String adapterType : adapterTypes) {
            List<AdapterConnection> connections = dispatch(new ListAdaptersConnections(adapterType)).getCollection();
            for (AdapterConnection connection : connections) {
                componentHelper.dispatchVisit(connection);
            }
            List<AdapterListener> listeners = dispatch(new ListListeners(adapterType)).getCollection();
            for (AdapterListener listener : listeners) {
                componentHelper.dispatchVisit(listener);
            }
            List<AdapterNotification> notifications = dispatch(new ListNotifications(adapterType)).getCollection();
            for (AdapterNotification notification : notifications) {
                componentHelper.dispatchVisit(notification);
            }
        }
    }

    private <A extends Action<R>, R extends Result> R dispatch(A action) throws ActionException {
        return dispatcher.execute(action);
    }
}
