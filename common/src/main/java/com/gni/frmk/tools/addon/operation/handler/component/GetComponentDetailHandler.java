package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 15:24
 *
 * @author: e03229
 */
public interface GetComponentDetailHandler
        <A extends GetComponentDetail<D, I>, I extends Id, D extends Detail, CTX extends ExecutionContext>
        extends ActionHandler<A, SingleResult<D>, CTX> {
}
