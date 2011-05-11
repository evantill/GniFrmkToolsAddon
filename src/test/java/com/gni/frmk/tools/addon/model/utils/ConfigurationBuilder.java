package com.gni.frmk.tools.addon.model.utils;

import com.gni.frmk.tools.addon.model.BaseComponentConfiguration;
import com.gni.frmk.tools.addon.model.Component.StateType;
import com.gni.frmk.tools.addon.model.Component.Type;
import com.gni.frmk.tools.addon.model.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.Configuration;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.ComposantType1.ConfigurationComposantType1;
import com.gni.frmk.tools.addon.model.component.ComposantType2.ConfigurationComposantType2;
import com.gni.frmk.tools.addon.model.component.ConnectableState.ConnectableStatus;
import com.gni.frmk.tools.addon.model.component.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection.AdapterConnectionDetail;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener.AdapterListenerDetail;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification.AdapterNotificationDetail;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger.JmsTriggerDetail;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState.TemporaryActivableState;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState.TemporaryStatus;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.model.component.root.Port.PortDetail;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;
import com.gni.frmk.tools.addon.model.component.root.Scheduler.SchedulerDetail;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState.SchedulerStatus;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class ConfigurationBuilder {

    private int index;
    private final Date now;

    public ConfigurationBuilder(int indexStart, Date now) {
        this.index = indexStart;
        this.now = now;
    }

    public Configuration newFullConfiguration() {
        Configuration cnf = new Configuration();
        cnf.setId("configuration_test_full");
        cnf.setCreation(now);
        cnf.setModification(now);
        cnf.setName("configuration_test_full");

        List<ComponentConfiguration> componentConfigurations = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<AdapterConnection, EnableState> elem = new BaseComponentConfiguration<AdapterConnection, EnableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newAdapterConnection());
            Map<StateType, EnableState> states = Maps.newHashMap();
            states.put(StateType.OPEN, new EnableState(EnableStatus.ENABLED));
            states.put(StateType.CLOSE, new EnableState(EnableStatus.DISABLED));
            states.put(StateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<AdapterListener, ActivableState> elem = new BaseComponentConfiguration<AdapterListener, ActivableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newAdapterListener());
            Map<StateType, ActivableState> states = Maps.newHashMap();
            states.put(StateType.OPEN, new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE));
            states.put(StateType.CLOSE, new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE));
            states.put(StateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<AdapterNotification, ActivableState> elem = new BaseComponentConfiguration<AdapterNotification, ActivableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newAdapterNotification());
            Map<StateType, ActivableState> states = Maps.newHashMap();
            states.put(StateType.OPEN, new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE));
            states.put(StateType.CLOSE, new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE));
            states.put(StateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<JmsAlias, ConnectableState> elem = new BaseComponentConfiguration<JmsAlias, ConnectableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newJmsAlias());
            Map<StateType, ConnectableState> states = Maps.newHashMap();
            states.put(StateType.OPEN, new ConnectableState(EnableStatus.ENABLED, ConnectableStatus.CONNECTED));
            states.put(StateType.CLOSE, new ConnectableState(EnableStatus.DISABLED, ConnectableStatus.DISCONNECTED));
            states.put(StateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<JmsTrigger, ActivableState> elem = new BaseComponentConfiguration<JmsTrigger, ActivableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newJmsTrigger());
            Map<StateType, ActivableState> states = Maps.newHashMap();
            states.put(StateType.OPEN, new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE));
            states.put(StateType.CLOSE, new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE));
            states.put(StateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<NativeTrigger, NativeTriggerState> elem = new BaseComponentConfiguration<NativeTrigger, NativeTriggerState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newNativeTrigger());
            Map<StateType, NativeTriggerState> states = Maps.newHashMap();

            TemporaryActivableState openTempState = new TemporaryActivableState(TemporaryStatus.PERMANENT, ActivableStatus.ACTIVE);
            TemporaryActivableState closeTempState = new TemporaryActivableState(TemporaryStatus.PERMANENT, ActivableStatus.INACTIVE);
            states.put(StateType.OPEN, NativeTriggerState.newNativeTriggerState(new EnableState(EnableStatus.ENABLED), openTempState, openTempState));
            states.put(StateType.CLOSE, NativeTriggerState.newNativeTriggerState(new EnableState(EnableStatus.DISABLED), closeTempState, closeTempState));
            states.put(StateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<Port, ActivableState> elem = new BaseComponentConfiguration<Port, ActivableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newPort());
            Map<StateType, ActivableState> states = Maps.newHashMap();
            states.put(StateType.OPEN, new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE));
            states.put(StateType.CLOSE, new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE));
            states.put(StateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<Scheduler, SchedulerState> elem = new BaseComponentConfiguration<Scheduler, SchedulerState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newScheduler());
            Map<StateType, SchedulerState> states = Maps.newHashMap();
            states.put(StateType.OPEN, new SchedulerState(EnableStatus.ENABLED, SchedulerStatus.UNEXPIRED));
            states.put(StateType.CLOSE, new SchedulerState(EnableStatus.DISABLED, SchedulerStatus.EXPIRED));
            states.put(StateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        cnf.setComponentConfigurations(componentConfigurations);
        return cnf;
    }

    public Configuration createConfiguration() {
        Configuration cnf = new Configuration();
        cnf.setName("test");
        cnf.setId("testId000");
        cnf.setCreation(now);
        cnf.setModification(now);
        cnf.setPackageName("Default");
        cnf.getComponentConfigurations().add(createComponent1());
        cnf.getComponentConfigurations().add(createComponent2());
        return cnf;
    }

    public ComponentConfiguration<?, ?> createComponent1() {
        ComposantType1 component = new ComposantType1();
        component.setType(Type.UNKNOWN);
        component.setId(new ComposantType1.Type1Id("component id"));
        component.setCurrentState(new ComposantType1.Type1State(true));
        component.setDetail(new SimpleDetail("description of component 1"));
        ConfigurationComposantType1 conf = new ConfigurationComposantType1();
        conf.setComponent(component);
        conf.getStateConfigurations().put(StateType.OPEN, new ComposantType1.Type1State(true));
        conf.getStateConfigurations().put(StateType.CLOSE, new ComposantType1.Type1State(false));
        conf.getStateConfigurations().put(StateType.CURRENT, component.getCurrentState());

        return conf;
    }

    public ComponentConfiguration<?, ?> createComponent2() {
        ComposantType2 component = new ComposantType2();
        component.setType(Type.UNKNOWN);
        component.setId(new ComposantType2.Type2Id(666));
        component.setCurrentState(new ComposantType2.Type2State(true, true));
        component.setDetail(new SimpleDetail("description of component 2"));

        ConfigurationComposantType2 conf = new ConfigurationComposantType2();
        conf.setComponent(component);
        conf.getStateConfigurations().put(StateType.OPEN, new ComposantType2.Type2State(true, true));
        conf.getStateConfigurations().put(StateType.CLOSE, new ComposantType2.Type2State(false, false));
        conf.getStateConfigurations().put(StateType.CURRENT, component.getCurrentState());

        return conf;
    }

    public Port newPort() {
        index++;
        Port result = new Port();
        result.setType(Type.PORT);
        result.setId(new PackageAndStringId(String.format("packageName_%d", index), String.format("port_key_%d", index)));
        result.setDetail(new PortDetail(true));
        result.setCurrentState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.INACTIVE));
        return result;
    }

    public NativeTrigger newNativeTrigger() {
        index++;
        NativeTrigger result = new NativeTrigger();
        result.setType(Type.NATIVE_TRIGGER);
        result.setId(new StringId(String.format("native_trigger_name_%d", index)));
        result.setDetail(NoDetail.newInstance());

        NativeTriggerState state = new NativeTriggerState();
        state.setEnabled(new EnableState(EnableStatus.ENABLED));
        state.setProcessingState(new TemporaryActivableState(TemporaryStatus.PERMANENT, ActivableStatus.INACTIVE));
        state.setRetrievalState(new TemporaryActivableState(TemporaryStatus.TEMPORARY, ActivableStatus.ACTIVE));
        result.setCurrentState(state);
        return result;
    }

    public JmsTrigger newJmsTrigger() {
        index++;
        JmsTrigger result = new JmsTrigger();
        result.setType(Type.JMS_TRIGGER);
        result.setId(new StringId(String.format("trigger_name%d", index)));
        result.setDetail(new JmsTriggerDetail(String.format("packageName_%d", index)));
        result.setCurrentState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE));
        return result;
    }

    public JmsAlias newJmsAlias() {
        index++;
        JmsAlias result = new JmsAlias();
        result.setType(Type.JMS_ALIAS);
        result.setId(new StringId(String.format("jms_alias_name%d", index)));
        result.setDetail(new JmsAliasDetail(String.format("description %d", index)));
        result.setCurrentState(new ConnectableState(EnableStatus.ENABLED, ConnectableStatus.CONNECTED));
        return result;
    }

    public AdapterConnection newAdapterConnection() {
        index++;
        AdapterConnection result = new AdapterConnection();
        result.setType(Type.ADAPTER_CONNECTION);
        result.setId(new AdapterId(String.format("alias_%d", index), "JDBCAdapter"));
        result.setDetail(new AdapterConnectionDetail(String.format("packageName_%d", index)));
        result.setCurrentState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE));
        return result;
    }

    public AdapterListener newAdapterListener() {
        index++;
        AdapterListener result = new AdapterListener();
        result.setType(Type.ADAPTER_LISTENER);
        result.setId(new AdapterId(String.format("listener_name_%d", index), "JDBCAdapter"));
        result.setDetail(new AdapterListenerDetail(String.format("packageName_%d", index)));
        result.setCurrentState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE));
        return result;
    }

    public AdapterNotification newAdapterNotification() {
        index++;
        AdapterNotification result = new AdapterNotification();
        result.setType(Type.ADAPTER_NOTIFICATION);
        result.setId(new AdapterId(String.format("notification_name_%d", index), "JDBCAdapter"));
        result.setDetail(new AdapterNotificationDetail(String.format("packageName_%d", index)));
        result.setCurrentState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE));
        return result;
    }

    public Scheduler newScheduler() {
        index++;
        Scheduler result = new Scheduler();
        result.setType(Type.SCHEDULER);
        result.setId(new StringId(String.format("oid_%d", index)));
        String type = String.format("schedulerType_%d", index);
        String name = String.format("name_%d", index);
        String service = String.format("service_%d", index);
        String description = String.format("description_%d", index);
        result.setDetail(new SchedulerDetail(type, name, service, description));
        result.setCurrentState(new SchedulerState(EnableStatus.ENABLED, SchedulerStatus.EXPIRED));
        return result;
    }

    public Configuration newSimpleConfiguration() {
        Configuration cnf = new Configuration();
        cnf.setId("configuration_test_full");
        cnf.setCreation(now);
        cnf.setModification(now);
        cnf.setName("configuration_test_full");

        List<ComponentConfiguration> componentConfigurations = Lists.newArrayList();
        BaseComponentConfiguration<AdapterConnection, EnableState> elem = new BaseComponentConfiguration<AdapterConnection, EnableState>();
        elem.setPresentOnIS(false);
        elem.setComponent(newAdapterConnection());
        Map<StateType, EnableState> states = Maps.newHashMap();
        states.put(StateType.OPEN, new EnableState(EnableStatus.ENABLED));
        states.put(StateType.CLOSE, new EnableState(EnableStatus.DISABLED));
        states.put(StateType.CURRENT, elem.getComponent().getCurrentState());
        elem.setStateConfigurations(states);
        componentConfigurations.add(elem);

        cnf.setComponentConfigurations(componentConfigurations);
        return cnf;
    }
}
