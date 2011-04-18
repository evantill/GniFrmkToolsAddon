package com.gni.frmk.tools.addon.result;

import com.gni.frmk.tools.addon.api.action.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:48
 *
 * @author: e03229
 */
public abstract class SingleResult<T>
        extends AbstractResult
        implements Result {
    private final T value;

    public SingleResult(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
