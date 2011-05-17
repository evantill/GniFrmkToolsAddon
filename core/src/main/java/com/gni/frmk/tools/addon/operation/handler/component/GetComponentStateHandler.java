package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
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
        <A extends GetComponentState<S, I>, I extends Id, S extends State, CTX extends ExecutionContext>
        extends ActionHandler<A, SingleResult<S>, CTX> {

}
