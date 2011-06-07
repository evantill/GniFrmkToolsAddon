package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class GetComponentState
        <T extends ComponentType<T, ?, I, S, ?>,
                I extends ComponentId<I>,
                S extends ComponentState<S>>
        extends IdTypeAwareAction<T,I>
        implements Action<SingleResult<S>> {

    public GetComponentState(T type, I id) {
        super(type, id);
    }
}
