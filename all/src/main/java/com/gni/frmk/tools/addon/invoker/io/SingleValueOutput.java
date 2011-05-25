package com.gni.frmk.tools.addon.invoker.io;

import com.gni.frmk.tools.addon.invoker.api.ServiceOutput;
import com.google.common.base.Preconditions;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 12:03
 *
 * @author: e03229
 */
public class SingleValueOutput<T> implements ServiceOutput {
    private final T value;

    private SingleValueOutput(T value) {
        this.value = Preconditions.checkNotNull(value);
    }

    public T getValue() {
        return value;
    }

    public static <T> SingleValueOutput<T> newInstance(T value) {
        return new SingleValueOutput<T>(value);
    }
}
