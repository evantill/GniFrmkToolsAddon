package com.gni.frmk.tools.addon.operation.handler.component.oldies;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 18:36
 *
 * @author: e03229
 */
public interface GetComponentStateHandler
        <A extends GetComponentState<T, I, S>,
                T extends ComponentType<T, ?, I, S, ?>,
                I extends ComponentId<I>, S extends ComponentState<S>, CTX extends ExecutionContext>
        extends ActionHandler<A, SingleResult<S>, CTX> {

}
