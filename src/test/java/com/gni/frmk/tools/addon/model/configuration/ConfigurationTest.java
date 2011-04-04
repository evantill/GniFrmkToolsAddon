package com.gni.frmk.tools.addon.model.configuration;

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
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
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

    private static final String DHMS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String NOW_DHMS = "2011-03-03 19:00:23.828";

    private static String xmlSimple = loadXml("simple");
    private static String xmlFull = loadXml("full");

    private static Locale savedLocale = Locale.getDefault();
    private static TimeZone savedTimeZone = TimeZone.getDefault();

    private static int index = 0;
    private static final String[] MODEL_PACKAGES = new String[]{
            "com.gni.frmk.tools.addon.model.configuration",
            "com.gni.frmk.tools.addon.model.component",
            "com.gni.frmk.tools.addon.model.component.detail",
            "com.gni.frmk.tools.addon.model.component.id",
            "com.gni.frmk.tools.addon.model.component.state",
    };
    private static final String JAXB_PACKAGES = buildJaxbPackages();

    private static String buildJaxbPackages(){
        StringBuilder builder =new StringBuilder();
        for(String p : MODEL_PACKAGES){
            builder.append(p).append(':');
        }
        return builder.toString();
    }

    @Before
    public void clear() {
        index = 0;
    }

    @Before
    public void xmlUnitConfiguration() {
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
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
        ComponentConfiguration<AdapterConnection, EnableState> cnxConf = ComponentConfiguration.builder(AdapterConnection.class)
                                                                                               .defineComponent(cnx)
                                                                                               .defineOpenState(new EnableState(EnableStatus.ENABLED))
                                                                                               .defineCloseState(new EnableState(EnableStatus.DISABLED))
                                                                                               .build();
        Configuration cnf = Configuration.builder()
                                         .create("name", now())
                                         .addAdapterConnection(cnxConf)
                                         .build();
    }

    @Test
    public void testSimpleMarshall() throws JAXBException, IOException, SAXException {
        Configuration cnf = newSimpleConfiguration();
        String out = marshal(cnf, true);
        assertXMLEqual(xmlSimple, out);
    }

    @Test
    public void testSimpleUnmMarshall() throws JAXBException {
        Configuration fromCnf = newSimpleConfiguration();
        String xml = marshal(fromCnf, true);
        Configuration cnf = unmarshall(xml);
        raiseExceptionIfInvalid(cnf);
        assertNotNull(cnf);
        assertEquals(1, cnf.getAdapterConnectionConfigurations().size());
    }

    @Test
    public void testFullMarshall() throws JAXBException, IOException, SAXException {
        Configuration cnf = newFullConfiguration();
        String out = marshal(cnf, true);
        assertXMLEqual(xmlFull, out);
    }

    @Test
    public void testFullUnmMarshall() throws JAXBException {
        Configuration fromCnf = newFullConfiguration();
        String xml = marshal(fromCnf, false);
        Configuration cnf = unmarshall(xml);
        raiseExceptionIfInvalid(cnf);
        assertNotNull(cnf);
        assertEquals(10, cnf.getAdapterConnectionConfigurations().size());
    }

    private Date now() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DHMS_PATTERN);
            return sdf.parse(NOW_DHMS);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String marshal(Configuration cnf, boolean prettyPrint) throws JAXBException {
        JAXBContext ctx = createJaxbContext();
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, prettyPrint);
        StringWriter out = new StringWriter();
        m.marshal(cnf, out);
        return out.toString();
    }

    private JAXBContext createJaxbContext() throws JAXBException {
        return JAXBContext.newInstance(JAXB_PACKAGES);
    }

    private Configuration unmarshall(String xml) throws JAXBException {
        JAXBContext ctx = createJaxbContext();
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
        builder.addAdapterConnection(newAdapterConnectionConfiguration());
        return builder.build();
    }

    private Configuration newFullConfiguration() {
        Builder builder = Configuration.builder().create("configuration_test_full", now());
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

    private ComponentConfiguration<Scheduler, SchedulerState> newSchedulerConfiguration() {
        return ComponentConfiguration.builder(Scheduler.class)
                                     .defineComponent(newScheduler())
                                     .defineOpenState(new SchedulerState(EnableStatus.ENABLED, SchedulerStatus.UNEXPIRED))
                                     .defineCloseState(new SchedulerState(EnableStatus.DISABLED, SchedulerStatus.EXPIRED))
                                     .build();
    }

    private ComponentConfiguration<Port, ActivableState> newPortConfiguration() {
        return ComponentConfiguration.builder(Port.class)
                                     .defineComponent(newPort())
                                     .defineOpenState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                                     .defineCloseState(new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE))
                                     .build();
    }

    private ComponentConfiguration<NativeTrigger, NativeTriggerState> newNativeTriggerConfiguration() {
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
        return ComponentConfiguration.builder(NativeTrigger.class)
                                     .defineComponent(newNativeTrigger())
                                     .defineOpenState(openState)
                                     .defineCloseState(closeState)
                                     .build();
    }

    private ComponentConfiguration<IntegrationServerPackage, EnableState> newPackageConfiguration() {
        return ComponentConfiguration.builder(IntegrationServerPackage.class)
                                     .defineComponent(newIntegrationServerPackage())
                                     .defineOpenState(new EnableState(EnableStatus.ENABLED))
                                     .defineCloseState(new EnableState(EnableStatus.DISABLED))
                                     .build();
    }

    private ComponentConfiguration<JmsTrigger, ActivableState> newJmsTriggerConfiguration() {
        return ComponentConfiguration.builder(JmsTrigger.class)
                                     .defineComponent(newJmsTrigger())
                                     .defineOpenState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                                     .defineCloseState(new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE))
                                     .build();
    }

    private ComponentConfiguration<JmsAlias, ConnectableState> newJmsAliasConfiguration() {
        return ComponentConfiguration.builder(JmsAlias.class)
                                     .defineComponent(newJmsAlias())
                                     .defineOpenState(new ConnectableState(EnableStatus.ENABLED, ConnectableStatus.CONNECTED))
                                     .defineCloseState(new ConnectableState(EnableStatus.DISABLED, ConnectableStatus.DISCONNECTED))
                                     .build();
    }

    private ComponentConfiguration<AdapterNotification, ActivableState> newAdapterNotificationConfiguration() {
        return ComponentConfiguration.builder(AdapterNotification.class)
                                     .defineComponent(newAdapterNotification())
                                     .defineOpenState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                                     .defineCloseState(new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE))
                                     .build();
    }

    private ComponentConfiguration<AdapterListener, ActivableState> newAdapterListenerConfiguration() {
        return ComponentConfiguration.builder(AdapterListener.class)
                                     .defineComponent(newAdapterListener())
                                     .defineOpenState(new ActivableState(EnableStatus.ENABLED, ActivableStatus.ACTIVE))
                                     .defineCloseState(new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE))
                                     .build();
    }

    private ComponentConfiguration<AdapterConnection, EnableState> newAdapterConnectionConfiguration() {
        return ComponentConfiguration.builder(AdapterConnection.class)
                                     .defineComponent(newAdapterConnection())
                                     .defineOpenState(new EnableState(EnableStatus.ENABLED))
                                     .defineCloseState(new EnableState(EnableStatus.DISABLED))
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

    private static String loadXml(final String name) {
        try {
            URL resource = ConfigurationTest.class.getResource(String.format("ConfigurationTest_%s.xml", name));
            return Resources.toString(resource, Charsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
