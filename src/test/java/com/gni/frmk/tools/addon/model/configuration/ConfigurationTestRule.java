package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.manager.ConfigurationSerializer;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState.ConnectableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState.SchedulerStatus;
import com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState.TemporaryStatus;
import com.gni.frmk.tools.addon.model.configuration.Configuration.Builder;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.rules.ExternalResource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 14:06
 *
 * @author: e03229
 */
public class ConfigurationTestRule extends ExternalResource {

    private static final String DHMS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String NOW_DHMS = "2011-03-03 19:00:23.828";

    private Locale savedLocale = Locale.getDefault();
    private TimeZone savedTimeZone = TimeZone.getDefault();
    private int index = 0;

    private static ConfigurationSerializer serializer;

    public static Configuration loadConfiguration(Class classToTest, String name) throws Exception {
        String xml = loadXml(name, classToTest);
        ConfigurationSerializer serializer = new ConfigurationSerializer();
        return serializer.loadConfiguration(new StringReader(xml));
    }

    public static String loadXml(final String name, final Class testClassName) {
        try {
            URL resource = testClassName.getResource(String.format("%s_%s.xml", testClassName.getSimpleName(), name));
            return Resources.toString(resource, Charsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void before() throws Throwable {
        savedLocale = Locale.getDefault();
        savedTimeZone = TimeZone.getDefault();
        Locale.setDefault(Locale.US);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        index = 0;
    }

    @Override
    protected void after() {
        Locale.setDefault(savedLocale);
        TimeZone.setDefault(savedTimeZone);
    }

    public Date now() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DHMS_PATTERN);
            return sdf.parse(NOW_DHMS);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<ConstraintViolation<Configuration>> validate(Configuration cnf) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(cnf);
    }

    public void raiseExceptionIfInvalid(Configuration cnf) {
        Set<ConstraintViolation<Configuration>> violations = validate(cnf);
        if (violations.size() > 0) {
            throw new RuntimeException(violations.toString());
        }
    }

    public Configuration newSimpleConfiguration() {
        Builder builder = Configuration.builder().create("WmRoot", "configuration_test_simple", now());
        builder.addAdapterConnection(newAdapterConnectionConfiguration());
        return builder.build();
    }

    public Configuration newFullConfiguration() {
        Builder builder = Configuration.builder().create("WmRoot", "configuration_test_full", now());
        for (int i = 0; i < 10; i++) {
            builder.addAdapterConnection(newAdapterConnectionConfiguration());
        }
        for (int i = 0; i < 10; i++) {
            builder.addAdapterListener(newAdapterListenerConfiguration());
        }
        for (int i = 0; i < 10; i++) {
            builder.addAdapterNotification(newAdapterNotificationConfiguration());
        }
        for (int i = 0; i < 10; i++) {
            builder.addJmsAliasConnection(newJmsAliasConfiguration());
        }
        for (int i = 0; i < 10; i++) {
            builder.addJmsTrigger(newJmsTriggerConfiguration());
        }
        for (int i = 0; i < 10; i++) {
            builder.addNativeTrigger(newNativeTriggerConfiguration());
        }
        for (int i = 0; i < 10; i++) {
            builder.addPackage(newPackageConfiguration());
        }
        for (int i = 0; i < 10; i++) {
            builder.addPort(newPortConfiguration());
        }
        for (int i = 0; i < 10; i++) {
            builder.addScheduler(newSchedulerConfiguration());
        }
        return builder.build();
    }

    private SchedulerConfiguration newSchedulerConfiguration() {
        return SchedulerConfiguration.builder()
                                     .defineComponent(newScheduler())
                                     .defineOpenState(new SchedulerState(EnableStatus.ENABLED, SchedulerStatus.UNEXPIRED))
                                     .defineCloseState(new SchedulerState(EnableStatus.DISABLED, SchedulerStatus.EXPIRED))
                                     .select(true)
                                     .exist(false)
                                     .build();
    }

    private PortConfiguration newPortConfiguration() {
        return PortConfiguration.builder()
                                .defineComponent(newPort())
                                .defineOpenState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                                .defineCloseState(new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE))
                                .select(true)
                                .exist(false)
                                .build();
    }

    private NativeTriggerConfiguration newNativeTriggerConfiguration() {
        NativeTriggerState openState = NativeTriggerState.builder()
                                                         .defineEnable(EnableStatus.ENABLED)
                                                         .defineProcessing(TemporaryStatus.PERMANENT, ActivableStatus.ACTIVE)
                                                         .defineRetrieval(TemporaryStatus.PERMANENT, ActivableStatus.ACTIVE)
                                                         .build();
        NativeTriggerState closeState = NativeTriggerState.builder()
                                                          .defineEnable(EnableStatus.DISABLED)
                                                          .defineProcessing(TemporaryStatus.PERMANENT, ActivableStatus.INACTIVE)
                                                          .defineRetrieval(TemporaryStatus.PERMANENT, ActivableStatus.INACTIVE)
                                                          .build();
        return NativeTriggerConfiguration.builder()
                                         .defineComponent(newNativeTrigger())
                                         .defineOpenState(openState)
                                         .defineCloseState(closeState)
                                         .select(true)
                                         .exist(false)
                                         .build();
    }

    private IntegrationServerPackageConfiguration newPackageConfiguration() {
        return IntegrationServerPackageConfiguration.builder()
                                                    .defineComponent(newIntegrationServerPackage())
                                                    .defineOpenState(new EnableState(EnableStatus.ENABLED))
                                                    .defineCloseState(new EnableState(EnableStatus.DISABLED))
                                                    .select(true)
                                                    .exist(false)
                                                    .build();
    }

    private JmsTriggerConfiguration newJmsTriggerConfiguration() {
        return JmsTriggerConfiguration.builder()
                                      .defineComponent(newJmsTrigger())
                                      .defineOpenState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                                      .defineCloseState(new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE))
                                      .select(true)
                                      .exist(false)
                                      .build();
    }

    private JmsAliasConfiguration newJmsAliasConfiguration() {
        return JmsAliasConfiguration.builder()
                                    .defineComponent(newJmsAlias())
                                    .defineOpenState(new ConnectableState(EnableStatus.ENABLED, ConnectableStatus.CONNECTED))
                                    .defineCloseState(new ConnectableState(EnableStatus.DISABLED, ConnectableStatus.DISCONNECTED))
                                    .select(true)
                                    .exist(false)
                                    .build();
    }

    private AdapterNotificationConfiguration newAdapterNotificationConfiguration() {
        return AdapterNotificationConfiguration.builder()
                                               .defineComponent(newAdapterNotification())
                                               .defineOpenState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                                               .defineCloseState(new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE))
                                               .select(true)
                                               .exist(false)
                                               .build();
    }

    private AdapterListenerConfiguration newAdapterListenerConfiguration() {
        return AdapterListenerConfiguration.builder()
                                           .defineComponent(newAdapterListener())
                                           .defineOpenState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                                           .defineCloseState(new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE))
                                           .select(true)
                                           .exist(false)
                                           .build();
    }

    private AdapterConnectionConfiguration newAdapterConnectionConfiguration() {
        return AdapterConnectionConfiguration.builder()
                                             .defineComponent(newAdapterConnection())
                                             .defineOpenState(new EnableState(EnableStatus.ENABLED))
                                             .defineCloseState(new EnableState(EnableStatus.DISABLED))
                                             .select(true)
                                             .exist(false)
                                             .build();
    }

    private Scheduler newScheduler() {
        index++;
        return Scheduler.builder()
                        .oid(String.format("oid_%d", index))
                        .schedulerType(String.format("schedulerType_%d", index))
                        .name(String.format("name_%d", index))
                        .service(String.format("service_%d", index))
                        .description(String.format("description_%d", index))
                        .defineState(new SchedulerState(EnableStatus.ENABLED, SchedulerStatus.EXPIRED))
                        .build();
    }

    private Port newPort() {
        index++;
        return Port.builder()
                   .key(String.format("port_key_%d", index))
                   .primary(true)
                   .packageName(String.format("packageName_%d", index))
                   .defineState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.INACTIVE))
                   .build();
    }

    private IntegrationServerPackage newIntegrationServerPackage() {
        index++;
        return IntegrationServerPackage.builder()
                                       .packageName(String.format("packageName_%d", index))
                                       .defineState(new EnableState(EnableStatus.ENABLED))
                                       .build();
    }

    private NativeTrigger newNativeTrigger() {
        index++;
        NativeTriggerState state = NativeTriggerState.builder()
                                                     .defineEnable(EnableStatus.ENABLED)
                                                     .defineRetrieval(TemporaryStatus.PERMANENT, ActivableStatus.INACTIVE)
                                                     .defineProcessing(TemporaryStatus.TEMPORARY, ActivableStatus.ACTIVE)
                                                     .build();
        return NativeTrigger.builder()
                            .name(String.format("native_trigger_name_%d", index))
                            .defineState(state)
                            .build();
    }

    private JmsTrigger newJmsTrigger() {
        index++;
        return JmsTrigger.builder()
                         .name(String.format("trigger_name%d", index))
                         .packageName(String.format("packageName_%d", index))
                         .defineState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                         .build();
    }

    private JmsAlias newJmsAlias() {
        index++;
        return JmsAlias.builder()
                       .name(String.format("jms_alias_name%d", index))
                       .description(String.format("description %d", index))
                       .defineState(new ConnectableState(EnableStatus.ENABLED, ConnectableStatus.CONNECTED))
                       .build();
    }

    private AdapterConnection newAdapterConnection() {
        index++;
        return AdapterConnection.builder()
                                .alias(String.format("alias_%d", index))
                                .adapterType("JDBCAdapter")
                                .packageName(String.format("packageName_%d", index))
                                .defineState(new EnableState(EnableStatus.ENABLED))
                                .build();
    }

    private AdapterListener newAdapterListener() {
        index++;
        return AdapterListener.builder()
                              .name(String.format("listener_name_%d", index))
                              .adapterType("JDBCAdapter")
                              .packageName(String.format("packageName_%d", index))
                              .defineState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                              .build();
    }

    private AdapterNotification newAdapterNotification() {
        index++;
        return AdapterNotification.builder()
                                  .name(String.format("notification_name_%d", index))
                                  .adapterType("JDBCAdapter")
                                  .packageName(String.format("packageName_%d", index))
                                  .defineState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                                  .build();
    }
}
