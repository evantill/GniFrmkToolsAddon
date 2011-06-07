package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentType;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 20:19
 *
 * @author: e03229
 */
public abstract class TypeAwareAction<T extends ComponentType<T,?,?,?,?>> {
    private final T type;

    protected TypeAwareAction(T type) {
        this.type = type;
    }

    public T getType() {
        return type;
    }
}
