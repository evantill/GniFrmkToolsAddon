package com.gni.frmk.tools.addon.configuration;

import com.gni.frmk.tools.addon.configuration.Configuration.Builder;
import com.gni.frmk.tools.addon.configuration.components.*;
import com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.configuration.components.ConnectableState.ConnectableStatus;
import com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.configuration.components.SchedulerState.SchedulerStatus;
import com.gni.frmk.tools.addon.configuration.components.TemporaryActivableState.TemporaryStatus;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 19:21
 *
 * @author: e03229
 */
public class ConfigurationTest {

    private final String xmlSimple = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><configuration name=\"configuration_test_simple\" creation=\"2011-03-03T19:00:23.828Z\" modification=\"2011-03-03T19:00:23.828Z\"><adapterConnections><adapterConnections><stringId>alias_1</stringId><type>ADAPTER_CONNECTION</type><enableState><enabled>ENABLED</enabled></enableState><details/><packageName>packageName_1</packageName><adapterType>JDBCAdapter</adapterType><alias>alias_1</alias></adapterConnections></adapterConnections><adapterListeners/><adapterNotifications/><integrationServerPackages/><jmsAliases/><jmsTriggers/><nativeTriggers/><ports/><schedulers/></configuration>";

    private static Locale savedLocale = Locale.getDefault();
    private static TimeZone savedTimeZone = TimeZone.getDefault();

    private static int index = 0;

    @Before
    public void clear() {
        index = 0;
    }

    @BeforeClass
    public static void utcLocals() {
        Locale.setDefault(Locale.US);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @AfterClass
    public static void resetLocals() {
        Locale.setDefault(savedLocale);
        TimeZone.setDefault(savedTimeZone);
    }

    @Test
    public void testValidation() {
        AdapterConnection cnx = AdapterConnection.builder()
                                                 .alias("alias")
                                                 .adapterType("adapterType")
                                                 .packageName("packageName")
                                                 .defineState(new EnableState(EnableStatus.ENABLED))
                                                 .build();
        Configuration cnf = Configuration.builder().create("name",now()).addAdapterConnection(cnx).buildAndValidate();
    }

    @Test
    public void testSimpleMarshall() throws JAXBException {
        Configuration cnf = newSimpleConfiguration();
        String out = marshal(cnf, false);
        assertEquals("marshalling error", xmlSimple, out);
    }

    @Test
    public void testSimpleUnmMarshall() throws JAXBException {
        Configuration fromCnf = newSimpleConfiguration();
        String xml = marshal(fromCnf, true);
        Configuration cnf = unmarshall(xml);
        raiseExceptionIfInvalid(cnf);
        assertNotNull(cnf);
        assertEquals(1, cnf.getAdapterConnections().size());
    }

    private Date now() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return sdf.parse("2011-03-03 19:00:23.828");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String marshal(Configuration cnf, boolean prettyPrint) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance("com.gni.frmk.tools.addon.configuration:com.gni.frmk.tools.addon.configuration.components");
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, prettyPrint);
        StringWriter out = new StringWriter();
        m.marshal(cnf, out);
        return out.toString();
    }

    private Configuration unmarshall(String xml) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance("com.gni.frmk.tools.addon.configuration:com.gni.frmk.tools.addon.configuration.components");
        Unmarshaller u = ctx.createUnmarshaller();
        StringReader in = new StringReader(xml);
        return (Configuration) u.unmarshal(in);
    }

    private Set<ConstraintViolation<Configuration>> validate(Configuration cnf) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(cnf);
    }

    private void raiseExceptionIfInvalid(Configuration cnf) {
        Set<ConstraintViolation<Configuration>> violations = validate(cnf);
        if (violations.size() > 0) {
            throw new RuntimeException(violations.toString());
        }
    }

    private Configuration newSimpleConfiguration() {
        Builder builder = Configuration.builder().create("configuration_test_simple", now());
        builder.addAdapterConnection(newAdapterConnection());
        return builder.build();
    }

    private Configuration newFullConfiguration() {
        Builder builder = Configuration.builder().create("configuration_test_full", now());
        for (int i = 0; i < 10; i++) {
            builder.addAdapterConnection(newAdapterConnection());
        }
        for (int i = 0; i < 10; i++) {
            builder.addAdapterListener(newAdapterListener());
        }
        for (int i = 0; i < 10; i++) {
            builder.addAdapterNotification(newAdapterNotification());
        }
        for (int i = 0; i < 10; i++) {
            builder.addJmsAliasConnection(newJmsAlias());
        }
        for (int i = 0; i < 10; i++) {
            builder.addJmsTrigger(newJmsTrigger());
        }
        for (int i = 0; i < 10; i++) {
            builder.addNativeTrigger(newNativeTrigger());
        }
        for (int i = 0; i < 10; i++) {
            builder.addPackage(newPackage());
        }
        for (int i = 0; i < 10; i++) {
            builder.addPort(newPort());
        }
        for (int i = 0; i < 10; i++) {
            builder.addScheduler(newScheduler());
        }
        return builder.build();
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

    private IntegrationServerPackage newPackage() {
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
                            .packageName(String.format("packageName_%d", index))
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
