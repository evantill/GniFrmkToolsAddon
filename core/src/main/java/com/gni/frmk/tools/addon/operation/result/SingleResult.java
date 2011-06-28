package com.gni.frmk.tools.addon.operation.result;

import com.gni.frmk.tools.addon.operation.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:48
 *
 * @author: e03229
 */
public class SingleResult<T>
        implements Result {
    private final T value;

    public SingleResult(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public static <T> SingleResult<T> newInstance(T value) {
        return new SingleResult<T>(value);
    }
}
