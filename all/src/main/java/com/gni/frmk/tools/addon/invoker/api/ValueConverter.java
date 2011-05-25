package com.gni.frmk.tools.addon.invoker.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:26
 *
 * @author: e03229
 */
public interface ValueConverter<F,T> {

    boolean canConvert(Object from);

    T convert(Object from);

    Class<T> getResultType();
}
