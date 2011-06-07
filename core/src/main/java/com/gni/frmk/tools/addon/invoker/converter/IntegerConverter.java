package com.gni.frmk.tools.addon.invoker.converter;

import com.gni.frmk.tools.addon.invoker.api.ValueConverter;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 16:17
 *
 * @author: e03229
 */
public enum IntegerConverter implements ValueConverter<Object, Integer> {
    singleton;

    @Override
    public boolean canConvert(Object from) {
        return String.class.isInstance(from) || Integer.class.isInstance(from);
    }

    @Override
    public Integer convert(Object from) {
        if (String.class.isInstance(from)) {
            return Integer.valueOf(String.class.cast(from));
        } else if (Integer.class.isInstance(from)) {
            return Integer.class.cast(from);
        } else {
            return null;
        }
    }

    @Override
    public Class<Integer> getResultType() {
        return Integer.class;
    }
}
