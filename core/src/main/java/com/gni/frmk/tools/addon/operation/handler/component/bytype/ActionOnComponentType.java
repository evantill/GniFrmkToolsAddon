package com.gni.frmk.tools.addon.operation.handler.component.bytype;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/06/11
 * Time: 17:09
 *
 * @author: e03229
 */
public abstract class ActionOnComponentType<T extends ComponentType<?, ?, ?, ?, ?>, R extends Result> implements Action<R> {
    private final T type;

    protected ActionOnComponentType(T type) {this.type = type;}

    public T getType() {
        return type;
    }
}
