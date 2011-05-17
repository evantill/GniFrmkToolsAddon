package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.ModelResourceManager;
import com.gni.frmk.tools.addon.model.ModelResourceManager.ResourceRegistration;
import com.gni.frmk.tools.addon.model.component.ComponentResource;
import com.gni.frmk.tools.addon.model.component.test.TestResource;
import com.gni.frmk.tools.addon.repository.ConfigurationSerializer;
import com.gni.frmk.tools.addon.repository.ConfigurationSerializer.SerializationException;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.rules.ExternalResource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.util.Date;
import java.util.List;
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

    private static final String NOW_DHMS = "2010-05-03T21:01:59Z";

    private ModelResourceManager resourceManager = new ModelResourceManager();
    private List<ResourceRegistration> registrations = Lists.newArrayList();
    private ConfigurationSerializer configurationSerializer;

    private final DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
    private final String nowAsString;
    private final Date now;
    private ConfigurationBuilder configurationBuilder;

    private Locale savedLocale = Locale.getDefault();
    private TimeZone savedTimeZone = TimeZone.getDefault();

    public ConfigurationTestRule() {
        this(NOW_DHMS);
    }

    protected ConfigurationTestRule(String nowAsString) {
        this.nowAsString = nowAsString;
        now = parser.parseDateTime(nowAsString).toDate();
    }

    protected ConfigurationTestRule(Date now) {
        this.now = now;
        nowAsString = parser.print(now.getTime());
    }

    public ConfigurationBuilder getConfigurationBuilder() {
        return configurationBuilder;
    }

    public void saveConfiguration(Configuration cnf, Writer destination) throws SerializationException {
        configurationSerializer.saveConfiguration(cnf, destination);
    }

    public Configuration loadConfiguration(Reader source) throws SerializationException {
        return configurationSerializer.loadConfiguration(source);
    }

    public Configuration loadConfiguration(Class classToTest, String name) throws SerializationException {
        String xml = loadXml(name, classToTest);
        return configurationSerializer.loadConfiguration(new StringReader(xml));
    }

    public String loadXml(final String name, final Class testClassName) {
        try {
            URL resource = testClassName.getResource(String.format("%s_%s.xml", testClassName.getSimpleName(), name));
            return Resources.toString(resource, Charsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void before() throws Throwable {
        registrations.add(resourceManager.register(new ConfigurationResource()));
        registrations.add(resourceManager.register(new ComponentResource()));
        registrations.add(resourceManager.register(new TestResource()));
        configurationSerializer = new ConfigurationSerializer(resourceManager);
        configurationBuilder = new ConfigurationBuilder(0, now());
        savedLocale = Locale.getDefault();
        savedTimeZone = TimeZone.getDefault();
        Locale.setDefault(Locale.US);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Override
    protected void after() {
        for (ResourceRegistration registration : registrations) {
            registration.unregister();
        }
        resourceManager = null;
        configurationBuilder = null;
        configurationSerializer = null;
        Locale.setDefault(savedLocale);
        TimeZone.setDefault(savedTimeZone);
    }

    public Date now() {
        return now;
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

    public ModelResourceManager getResourceManager() {
        return resourceManager;
    }
}
