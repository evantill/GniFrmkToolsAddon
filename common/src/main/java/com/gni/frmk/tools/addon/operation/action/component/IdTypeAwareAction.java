package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.TypeAwareAction;
import com.gni.frmk.tools.addon.operation.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 20:26
 *
 * @author: e03229
 */
public abstract class IdTypeAwareAction
        <T extends ComponentType<T, ?, I, ?, ?>,
                I extends ComponentId<I>
                , R extends Result>
        extends TypeAwareAction<T, R> {

    private final I id;

    protected IdTypeAwareAction(T type, I id) {
        super(type);
        this.id = id;
    }

    public I getId() {
        return id;
    }
}
