package com.gni.frmk.tools.addon.util;

import org.junit.rules.ExternalResource;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 15:09
 *
 * @author: e03229
 */
public class ConstantLocaleRule extends ExternalResource {

    private Locale savedLocale = Locale.getDefault();
    private TimeZone savedTimeZone = TimeZone.getDefault();

    private final Locale requiredLocale;
    private final TimeZone requiredTimeZone;

    public ConstantLocaleRule(Locale requiredLocale, TimeZone requiredTimeZone) {
        this.requiredLocale = requiredLocale;
        this.requiredTimeZone = requiredTimeZone;
    }

    public static final ConstantLocaleRule utc_and_us_rule() {
        return new ConstantLocaleRule(Locale.US, TimeZone.getTimeZone("UTC"));
    }

    @Override
    protected void before() throws Throwable {
        savedLocale = Locale.getDefault();
        savedTimeZone = TimeZone.getDefault();
        Locale.setDefault(requiredLocale);
        TimeZone.setDefault(requiredTimeZone);
    }

    @Override
    protected void after() {
        Locale.setDefault(savedLocale);
        savedLocale = null;
        TimeZone.setDefault(savedTimeZone);
        savedTimeZone = null;
    }
}
