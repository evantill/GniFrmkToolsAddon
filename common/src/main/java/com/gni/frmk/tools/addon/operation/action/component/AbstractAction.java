package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/06/11
 * Time: 16:02
 *
 * @author: e03229
 */
public abstract class AbstractAction
        <T extends ComponentType<?, ?, ?, ?, ?>, R extends Result>
        implements Action<R> {
    private final T type;

    protected AbstractAction(T type) {
        this.type = type;
    }

    public T getType() {
        return type;
    }
}
