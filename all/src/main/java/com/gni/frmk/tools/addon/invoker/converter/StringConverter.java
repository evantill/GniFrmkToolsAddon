package com.gni.frmk.tools.addon.invoker.converter;

import com.gni.frmk.tools.addon.invoker.api.ValueConverter;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:27
 *
 * @author: e03229
 */
public enum StringConverter implements ValueConverter<Object, String> {
    singleton;

    @Override
    public boolean canConvert(Object from) {
        return true;
    }

    @Override
    public String convert(Object from) {
        if (from == null) {
            return null;
        }
        if (String.class.isInstance(from)) {
            return String.class.cast(from);
        }
        return from.toString();
    }

    @Override
    public Class<String> getResultType() {
        return String.class;
    }
}
