package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.action.configuration.CurrentConfiguration;
import com.gni.frmk.tools.addon.action.wm.art.RetrieveAdapterTypesList;
import com.gni.frmk.tools.addon.action.wm.art.connection.ListAdaptersConnections;
import com.gni.frmk.tools.addon.action.wm.art.listener.ListListeners;
import com.gni.frmk.tools.addon.action.wm.art.notifications.ListNotifications;
import com.gni.frmk.tools.addon.action.wm.jms.alias.GetJmsAliasReport;
import com.gni.frmk.tools.addon.action.wm.jms.trigger.GetJmsTriggerReport;
import com.gni.frmk.tools.addon.action.wm.root.ispackage.PackageList;
import com.gni.frmk.tools.addon.action.wm.root.port.ListPortListeners;
import com.gni.frmk.tools.addon.action.wm.root.scheduler.GetUserTaskList;
import com.gni.frmk.tools.addon.action.wm.root.trigger.GetNativeTriggerReport;
import com.gni.frmk.tools.addon.api.action.*;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeDispatcher;
import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationRepository;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration.MutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.gni.frmk.tools.addon.result.ConfigurationResult;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
 * Date: 11/04/11
 * Time: 13:27
 *
 * @author: e03229
 */
public class CurrentConfigurationHandler
        implements ActionHandler<CurrentConfiguration, ConfigurationResult, ExecutionContext> {

    private static final String DEFAULT_CONFIGURATION_ID = "default";
    private static final String DEFAULT_PACKAGE = "Default";
    private final InvokeDispatcher dispatcher;
    private final ConfigurationRepository repository;

    public CurrentConfigurationHandler(InvokeDispatcher dispatcher, ConfigurationRepository repository) {
        this.dispatcher = dispatcher;
        this.repository = repository;
    }

    @Override
    public Class<CurrentConfiguration> getActionType() {
        return CurrentConfiguration.class;
    }

    @Override
    public ConfigurationResult execute(CurrentConfiguration action, ExecutionContext context) throws ActionException {
        final List<ActionException> exceptions = Lists.newArrayList();
        MutableConfiguration builder = ImmutableConfiguration.builder();
        builder.create(DEFAULT_PACKAGE, DEFAULT_CONFIGURATION_ID, new Date());
        ConfigurationVisitor configurationVisitor = new ConfigurationVisitorAdapter(builder, action, exceptions);
        ConfigurationVisitor componentVisitor = new ComponentVisitorAdapter(configurationVisitor, action, exceptions);
        visitAllComponents(componentVisitor, action, exceptions);
        ConfigurationResult result = new ConfigurationResult(builder.build());
        result.addAllErrors(exceptions);
        return result;
    }

    private <A extends Action<R>, R extends Result> R dispatch(A action) throws DispatchException {
        return dispatcher.execute(action);
    }

    private void visitAllComponents(ConfigurationVisitor visitor,
            CurrentConfiguration action,
            List<ActionException> exceptions) {
        visitRootComponents(visitor, action, exceptions);
        visitArtComponents(visitor, action, exceptions);
        visitJmsComponents(visitor, action, exceptions);
    }

    private void visitJmsComponents(ConfigurationVisitor visitor,
            CurrentConfiguration action,
            List<ActionException> exceptions) {
        try {
            List<ImmutableJmsAlias> aliases = dispatch(new GetJmsAliasReport()).getCollection();
            for (ImmutableJmsAlias alias : aliases) {
                visitor.dispatchVisit(alias);
            }
        } catch (DispatchException e) {
            exceptions.add(new ActionException(action, e));
        }
        try {
            List<ImmutableJmsTrigger> jmsTriggers = dispatch(new GetJmsTriggerReport()).getCollection();
            for (ImmutableJmsTrigger trigger : jmsTriggers) {
                visitor.dispatchVisit(trigger);
            }
        } catch (DispatchException e) {
            exceptions.add(new ActionException(action, e));
        }
    }

    private void visitRootComponents(ConfigurationVisitor visitor,
            CurrentConfiguration action,
            List<ActionException> exceptions) {
        try {
            Set<ImmutableIntegrationServerPackage> packages = dispatch(new PackageList()).getCollection();
            for (ImmutableIntegrationServerPackage pkg : packages) {
                visitor.dispatchVisit(pkg);
            }
        } catch (DispatchException e) {
            exceptions.add(new ActionException(action, e));
        }
        try {

            List<ImmutablePort> ports = dispatch(new ListPortListeners()).getCollection();
            for (ImmutablePort port : ports) {
                visitor.dispatchVisit(port);
            }
        } catch (DispatchException e) {
            exceptions.add(new ActionException(action, e));
        }
        try {
            List<ImmutableScheduler> schedulers = dispatch(new GetUserTaskList()).getCollection();
            for (ImmutableScheduler scheduler : schedulers) {
                visitor.dispatchVisit(scheduler);
            }
        } catch (DispatchException e) {
            exceptions.add(new ActionException(action, e));
        }
        try {
            List<ImmutableNativeTrigger> nativeTriggers = dispatch(new GetNativeTriggerReport()).getCollection();
            for (ImmutableNativeTrigger trigger : nativeTriggers) {
                visitor.dispatchVisit(trigger);
            }
        } catch (DispatchException e) {
            exceptions.add(new ActionException(action, e));
        }
    }

    private void visitArtComponents(ConfigurationVisitor visitor,
            CurrentConfiguration action,
            List<ActionException> exceptions) {
        try {
            Set<String> adapterTypes = dispatch(new RetrieveAdapterTypesList()).getCollection();
            for (String adapterType : adapterTypes) {
                List<ImmutableAdapterConnection> connections = dispatch(new ListAdaptersConnections(adapterType)).getCollection();
                for (ImmutableAdapterConnection connection : connections) {
                    visitor.dispatchVisit(connection);
                }
                List<ImmutableAdapterListener> listeners = dispatch(new ListListeners(adapterType)).getCollection();
                for (ImmutableAdapterListener listener : listeners) {
                    visitor.dispatchVisit(listener);
                }
                List<ImmutableAdapterNotification> notifications = dispatch(new ListNotifications(adapterType)).getCollection();
                for (ImmutableAdapterNotification notification : notifications) {
                    visitor.dispatchVisit(notification);
                }
            }
        } catch (DispatchException e) {
            exceptions.add(new ActionException(action, e));
        }
    }

    private final class ComponentVisitorAdapter implements ConfigurationVisitor {
        private final ConfigurationVisitor adaptee;
        private final CurrentConfiguration action;
        private final List<ActionException> exceptions;

        private ComponentVisitorAdapter(ConfigurationVisitor adaptee,
                CurrentConfiguration action,
                List<ActionException> exceptions) {
            this.adaptee = adaptee;
            this.action = action;
            this.exceptions = exceptions;
        }

        @Override
        public void notifyError(Throwable error, ConfigurationVisited visited) {
            exceptions.add(new ActionException(action, error));
        }

        @Override
        public void dispatchVisit(ConfigurationVisited visitable) {
            visitable.accept(this);
        }

        @Override
        public void visitComponent(ImmutableAdapterConnection visited) {
            AdapterConnectionConfiguration.Builder builder = AdapterConnectionConfiguration.builder();
            AdapterConnectionConfiguration conf = builder.defineComponent(visited)
                                                         .exist(true)
                                                         .select(true)
                                                         .defineCloseState(new EnableState(DISABLED))
                                                         .defineOpenState(new EnableState(ENABLED))
                                                         .build();
            adaptee.visitComponentConfiguration(conf);
        }

        @Override
        public void visitComponent(ImmutableAdapterListener visited) {
            AdapterListenerConfiguration.Builder builder = AdapterListenerConfiguration.builder();
            AdapterListenerConfiguration cnf = builder.defineComponent(visited)
                                                      .exist(true)
                                                      .select(true)
                                                      .defineCloseState(new ActivableState(DISABLED, INACTIVE))
                                                      .defineOpenState(new ActivableState(ENABLED, ACTIVE))
                                                      .build();
            adaptee.visitComponentConfiguration(cnf);
        }

        @Override
        public void visitComponent(ImmutableAdapterNotification visited) {
            AdapterNotificationConfiguration.Builder builder = AdapterNotificationConfiguration.builder();
            AdapterNotificationConfiguration cnf = builder.defineComponent(visited)
                                                          .exist(true)
                                                          .select(true)
                                                          .defineCloseState(new ActivableState(DISABLED, INACTIVE))
                                                          .defineOpenState(new ActivableState(ENABLED, ACTIVE))
                                                          .build();
            adaptee.visitComponentConfiguration(cnf);
        }

        @Override
        public void visitComponent(ImmutablePort visited) {
            PortConfiguration.Builder builder = PortConfiguration.builder();
            PortConfiguration cnf = builder.defineComponent(visited)
                                           .exist(true)
                                           .select(true)
                                           .defineCloseState(new ActivableState(DISABLED, INACTIVE))
                                           .defineOpenState(new ActivableState(ENABLED, ACTIVE))
                                           .build();
            adaptee.visitComponentConfiguration(cnf);
        }

        @Override
        public void visitComponent(ImmutableScheduler visited) {
            SchedulerConfiguration.Builder builder = SchedulerConfiguration.builder();
            SchedulerConfiguration cnf = builder.defineComponent(visited)
                                                .exist(true)
                                                .select(true)
                                                .defineCloseState(new SchedulerState(DISABLED, EXPIRED))
                                                .defineOpenState(new SchedulerState(ENABLED, UNEXPIRED))
                                                .build();
            adaptee.visitComponentConfiguration(cnf);
        }

        @Override
        public void visitComponent(ImmutableNativeTrigger visited) {
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
            adaptee.visitComponentConfiguration(cnf);
        }

        @Override
        public void visitComponent(ImmutableJmsTrigger visited) {
            JmsTriggerConfiguration.Builder builder = JmsTriggerConfiguration.builder();
            JmsTriggerConfiguration cnf = builder.defineComponent(visited)
                                                 .exist(true)
                                                 .select(true)
                                                 .defineCloseState(new ActivableState(DISABLED, INACTIVE))
                                                 .defineOpenState(new ActivableState(ENABLED, ACTIVE))
                                                 .build();
            adaptee.visitComponentConfiguration(cnf);
        }

        @Override
        public void visitComponent(ImmutableJmsAlias visited) {
            JmsAliasConfiguration.Builder builder = JmsAliasConfiguration.builder();
            JmsAliasConfiguration cnf = builder.defineComponent(visited)
                                               .exist(true)
                                               .select(true)
                                               .defineCloseState(new ConnectableState(DISABLED, DISCONNECTED))
                                               .defineOpenState(new ConnectableState(ENABLED, CONNECTED))
                                               .build();
            adaptee.visitComponentConfiguration(cnf);
        }

        @Override
        public void visitComponent(ImmutableIntegrationServerPackage visited) {
            IntegrationServerPackageConfiguration.Builder builder = IntegrationServerPackageConfiguration.builder();
            IntegrationServerPackageConfiguration cnf = builder.defineComponent(visited)
                                                               .exist(true)
                                                               .select(true)
                                                               .defineCloseState(new EnableState(DISABLED))
                                                               .defineOpenState(new EnableState(ENABLED))
                                                               .build();
            adaptee.visitComponentConfiguration(cnf);
        }
    }

    private final class ConfigurationVisitorAdapter implements ConfigurationVisitor {
        private final MutableConfiguration adaptee;
        private final CurrentConfiguration action;
        private final List<ActionException> exceptions;

        private ConfigurationVisitorAdapter(
                MutableConfiguration adaptee,
                CurrentConfiguration action,
                List<ActionException> exceptions) {
            this.adaptee = adaptee;
            this.action = action;
            this.exceptions = exceptions;
        }

        @Override
        public void dispatchVisit(ConfigurationVisited visitable) {
            visitable.accept(this);
        }

        @Override
        public void visitComponentConfiguration(AdapterConnectionConfiguration visited) {
            adaptee.addAdapterConnection(visited);
        }

        @Override
        public void visitComponentConfiguration(AdapterListenerConfiguration visited) {
            adaptee.addAdapterListener(visited);
        }

        @Override
        public void visitComponentConfiguration(AdapterNotificationConfiguration visited) {
            adaptee.addAdapterNotification(visited);
        }

        @Override
        public void visitComponentConfiguration(PortConfiguration visited) {
            adaptee.addPort(visited);
        }

        @Override
        public void visitComponentConfiguration(SchedulerConfiguration visited) {
            adaptee.addScheduler(visited);
        }

        @Override
        public void visitComponentConfiguration(NativeTriggerConfiguration visited) {
            adaptee.addNativeTrigger(visited);
        }

        @Override
        public void visitComponentConfiguration(JmsTriggerConfiguration visited) {
            adaptee.addJmsTrigger(visited);
        }

        @Override
        public void visitComponentConfiguration(JmsAliasConfiguration visited) {
            adaptee.addJmsAliasConnection(visited);
        }

        @Override
        public void visitComponentConfiguration(IntegrationServerPackageConfiguration visited) {
            adaptee.addPackage(visited);
        }

        @Override
        public void notifyError(Throwable error, ConfigurationVisited visited) {
            exceptions.add(new ActionException(action, error));
        }

    }

}
