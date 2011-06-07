package com.gni.frmk.tools.addon.invoker.io;

import com.gni.frmk.tools.addon.invoker.api.ServiceInput;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 16:39
 *
 * @author: e03229
 */
public class SingleValueInput<T> implements ServiceInput {
    private final T value;

    private SingleValueInput(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public static final <T> SingleValueInput<T> newInstance(T value) {
        return new SingleValueInput<T>(value);
    }
}
