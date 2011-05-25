package com.gni.frmk.tools.addon.invoker.converter;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 11:30
 *
 * @author: e03229
 */
public class CastConverterTest {
    @Test
    public void testCanConvert() throws Exception {
        CastConverter<String> converter = CastConverter.newStrngInstance();
        String value="toto";
        boolean canConvert = converter.canConvert(value);
        Assert.assertTrue(canConvert);
    }
}
