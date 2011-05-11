package com.gni.frmk.tools.addon.model;

import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationSerializer;
import com.gni.frmk.tools.addon.model.Configuration;
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

    public static final String DHMS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String NOW_DHMS = "2011-03-03 19:00:23.828";

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
}
