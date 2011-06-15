package com.gni.frmk.tools.addon.model.configuration.test.oldies;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification;
import com.gni.frmk.tools.addon.model.component.jms.ConnectableState;
import com.gni.frmk.tools.addon.model.component.jms.ConnectableStatus;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsAliasDetail;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.root.*;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;
import com.gni.frmk.tools.addon.model.configuration.base.BaseComponentConfiguration;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 12:36
 *
 * @author: e03229
 */
public class ConfigurationFullBuilder {

    private int index;
    private final Date now;

    public ConfigurationFullBuilder(int indexStart, Date now) {
        this.index = indexStart;
        this.now = now;
    }

    private SchedulerState newCloseSchedulerState() {
        return SchedulerState.builder()
                             .scheduled(SchedulerStatus.EXPIRED)
                             .suspended(SuspendedStatus.SUSPENDED)
                             .validate()
                             .build();
    }

    private SchedulerState newOpenSchedulerState() {
        return SchedulerState.builder()
                             .scheduled(SchedulerStatus.UNEXPIRED)
                             .suspended(SuspendedStatus.READY)
                             .validate()
                             .build();
    }

    private NativeTriggerState newCloseNativeTriggerState(TemporaryActivableState closeTempState) {
        return NativeTriggerState.builder()
                                 .enabled(EnableState.builder().enable(EnableStatus.DISABLED).validate().build())
                                 .processingState(closeTempState)
                                 .retrievalState(closeTempState)
                                 .validate().build();
    }

    private NativeTriggerState newOpenNativeTriggerState(TemporaryActivableState openTempState) {
        return NativeTriggerState.builder()
                                 .enabled(EnableState.builder().enable(EnableStatus.ENABLED).validate().build())
                                 .processingState(openTempState)
                                 .retrievalState(openTempState)
                                 .validate().build();
    }

    private TemporaryActivableState newCloseTemporaryActivableState() {
        return newTemporaryActivableState(ActivableStatus.INACTIVE, TemporaryStatus.PERMANENT);
    }

    private TemporaryActivableState newOpenTemporaryActivableState() {
        return newTemporaryActivableState(ActivableStatus.ACTIVE, TemporaryStatus.PERMANENT);
    }

    private TemporaryActivableState newTemporaryActivableState(ActivableStatus active, TemporaryStatus permanent) {
        return TemporaryActivableState.builder()
                                      .activable(active)
                                      .temporary(permanent)
                                      .validate()
                                      .build();
    }

    private ConnectableState newCloseConnectableState() {
        return ConnectableState.build(EnableStatus.DISABLED, ConnectableStatus.DISCONNECTED);
    }

    private ConnectableState newOpenConnectableState() {
        return ConnectableState.build(EnableStatus.ENABLED, ConnectableStatus.CONNECTED);
    }

    private ActivableState newCloseActivableState() {
        return ActivableState.build(EnableStatus.DISABLED, ActivableStatus.INACTIVE);
    }

    private ActivableState newOpenActivableState() {
        return ActivableState.build(EnableStatus.ENABLED, ActivableStatus.ACTIVE);
    }

    public Port newPort() {
        index++;
        return Port.builder()
                   .id(PackageAndStringId.newInstance(String.format("packageName_%d", index), String.format("port_key_%d", index)))
                   .detail(PortDetail.builder().primary(true).validate().build())
                   .state(ActivableState.build(EnableStatus.ENABLED, ActivableStatus.INACTIVE))
                   .validate().build();
    }

    public NativeTrigger newNativeTrigger() {
        index++;
        return NativeTrigger.builder()
                            .id(StringId.build(String.format("native_trigger_name_%d", index)))
                            .detail(NoDetail.newInstance())
                            .state(NativeTriggerState.builder()
                                                     .enabled(EnableState.build(EnableStatus.ENABLED))
                                                     .processingState(newCloseTemporaryActivableState())
                                                     .retrievalState(newTemporaryActivableState(ActivableStatus.ACTIVE, TemporaryStatus.TEMPORARY))
                                                     .validate().build())
                            .validate().build();
    }

    public JmsTrigger newJmsTrigger() {
        index++;
        return JmsTrigger.builder()
                         .id(StringId.build(String.format("trigger_name%d", index)))
                         .state(newOpenActivableState())
                         .detail(PackageDetail.build(String.format("packageName_%d", index)))
                         .validate().build();
    }

    public JmsAlias newJmsAlias() {
        index++;
        return JmsAlias.builder()
                       .id(StringId.build(String.format("jms_alias_name%d", index)))
                       .detail(JmsAliasDetail.build(String.format("description %d", index)))
                       .state(newOpenConnectableState())
                       .validate().build();
    }

    public AdapterConnection newAdapterConnection() {
        index++;
        return AdapterConnection.builder()
                                .id(AdapterId.newInstance(String.format("alias_%d", index), "JDBCAdapter"))
                                .detail(PackageDetail.build(String.format("packageName_%d", index)))
                                .state(EnableState.build(EnableStatus.ENABLED))
                                .validate().build();
    }

    public AdapterListener newAdapterListener() {
        index++;
        return AdapterListener.builder()
                              .id(AdapterId.newInstance(String.format("listener_name_%d", index), "JDBCAdapter"))
                              .detail(PackageDetail.build(String.format("packageName_%d", index)))
                              .state(newOpenActivableState())
                              .validate().build();
    }

    public AdapterNotification newAdapterNotification() {
        index++;
        return AdapterNotification.builder()
                                  .id(AdapterId.newInstance(String.format("notification_name_%d", index), "JDBCAdapter"))
                                  .detail(PackageDetail.build(String.format("packageName_%d", index)))
                                  .state(newOpenActivableState())
                                  .validate().build();
    }

    public Scheduler newScheduler() {
        index++;
        return Scheduler.builder()
                        .id(StringId.build(String.format("oid_%d", index)))
                        .detail(SchedulerDetail.builder()
                                               .schedulerType(String.format("schedulerType_%d", index))
                                               .name(String.format("name_%d", index))
                                               .description(String.format("description_%d", index))
                                               .service(String.format("service_%d", index))
                                               .validate().build()
                        )
                        .state(SchedulerState.build(SuspendedStatus.READY, SchedulerStatus.EXPIRED))
                        .validate().build();
    }


    public Configuration buildFullConfiguration() {
        Configuration cnf = new Configuration();
        cnf.setId(new ConfigurationId("configuration_test_full", "Default"));
        cnf.setCreation(now);
        cnf.setModification(now);
        cnf.setName("configuration_test_full");

        List<ComponentConfiguration> componentConfigurations = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<AdapterConnection, EnableState> elem = new BaseComponentConfiguration<AdapterConnection, EnableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newAdapterConnection());
            Map<ComponentStateType, EnableState> states = Maps.newHashMap();
            states.put(ComponentStateType.OPEN, EnableState.build(EnableStatus.ENABLED));
            states.put(ComponentStateType.CLOSE, EnableState.build(EnableStatus.DISABLED));
            states.put(ComponentStateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<AdapterListener, ActivableState> elem = new BaseComponentConfiguration<AdapterListener, ActivableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newAdapterListener());
            Map<ComponentStateType, ActivableState> states = Maps.newHashMap();
            states.put(ComponentStateType.OPEN, newOpenActivableState());
            states.put(ComponentStateType.CLOSE, newCloseActivableState());
            states.put(ComponentStateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<AdapterNotification, ActivableState> elem = new BaseComponentConfiguration<AdapterNotification, ActivableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newAdapterNotification());
            Map<ComponentStateType, ActivableState> states = Maps.newHashMap();
            states.put(ComponentStateType.OPEN, newOpenActivableState());
            states.put(ComponentStateType.CLOSE, newCloseActivableState());
            states.put(ComponentStateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<JmsAlias, ConnectableState> elem = new BaseComponentConfiguration<JmsAlias, ConnectableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newJmsAlias());
            Map<ComponentStateType, ConnectableState> states = Maps.newHashMap();
            states.put(ComponentStateType.OPEN, newOpenConnectableState());
            states.put(ComponentStateType.CLOSE, newCloseConnectableState());
            states.put(ComponentStateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<JmsTrigger, ActivableState> elem = new BaseComponentConfiguration<JmsTrigger, ActivableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newJmsTrigger());
            Map<ComponentStateType, ActivableState> states = Maps.newHashMap();
            states.put(ComponentStateType.OPEN, newOpenActivableState());
            states.put(ComponentStateType.CLOSE, newCloseActivableState());
            states.put(ComponentStateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<NativeTrigger, NativeTriggerState> elem = new BaseComponentConfiguration<NativeTrigger, NativeTriggerState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newNativeTrigger());
            Map<ComponentStateType, NativeTriggerState> states = Maps.newHashMap();

            TemporaryActivableState openTempState = newOpenTemporaryActivableState();
            TemporaryActivableState closeTempState = newCloseTemporaryActivableState();
            states.put(ComponentStateType.OPEN, newOpenNativeTriggerState(openTempState));
            states.put(ComponentStateType.CLOSE, newCloseNativeTriggerState(closeTempState));
            states.put(ComponentStateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<Port, ActivableState> elem = new BaseComponentConfiguration<Port, ActivableState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newPort());
            Map<ComponentStateType, ActivableState> states = Maps.newHashMap();
            states.put(ComponentStateType.OPEN, newOpenActivableState());
            states.put(ComponentStateType.CLOSE, newCloseActivableState());
            states.put(ComponentStateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        for (int i = 0; i < 10; i++) {
            BaseComponentConfiguration<Scheduler, SchedulerState> elem = new BaseComponentConfiguration<Scheduler, SchedulerState>();
            elem.setPresentOnIS(false);
            elem.setComponent(newScheduler());
            Map<ComponentStateType, SchedulerState> states = Maps.newHashMap();
            states.put(ComponentStateType.OPEN, newOpenSchedulerState());
            states.put(ComponentStateType.CLOSE, newCloseSchedulerState());
            states.put(ComponentStateType.CURRENT, elem.getComponent().getCurrentState());
            elem.setStateConfigurations(states);
            componentConfigurations.add(elem);
        }
        cnf.setComponentConfigurations(componentConfigurations);
        return cnf;
    }

}
