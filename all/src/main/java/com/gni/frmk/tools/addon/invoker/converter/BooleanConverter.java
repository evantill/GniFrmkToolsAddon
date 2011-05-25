package com.gni.frmk.tools.addon.invoker.converter;

import com.gni.frmk.tools.addon.invoker.api.ValueConverter;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 16:17
 *
 * @author: e03229
 */
public enum BooleanConverter implements ValueConverter<Object, Boolean> {
    singleton;

    @Override
    public boolean canConvert(Object from) {
        return String.class.isInstance(from) || Boolean.class.isInstance(from);
    }

    @Override
    public Boolean convert(Object from) {
        if (String.class.isInstance(from)) {
            return Boolean.valueOf(String.class.cast(from));
        } else if (Boolean.class.isInstance(from)) {
            return Boolean.class.cast(from);
        } else {
            return null;
        }
    }

    @Override
    public Class<Boolean> getResultType() {
        return Boolean.class;
    }
}
