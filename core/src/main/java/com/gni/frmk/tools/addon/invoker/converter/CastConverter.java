package com.gni.frmk.tools.addon.invoker.converter;

import com.gni.frmk.tools.addon.invoker.api.ValueConverter;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 11:16
 *
 * @author: e03229
 */
public class CastConverter<T> implements ValueConverter<Object, T> {

    private final Class<T> type;

    public CastConverter(Class<T> type) {
        this.type = type;
    }

    @Override
    public boolean canConvert(Object from) {
        return type.isInstance(from);
    }

    @Override
    public T convert(Object from) {
        return type.cast(from);
    }

    @Override
    public Class<T> getResultType() {
        return type;
    }

    public static <T> CastConverter<T> newInstance(Class<T> type) {
        return new CastConverter<T>(type);
    }

    public static CastConverter<String> newStrngInstance() {
        return new CastConverter(String.class);
    }
}
