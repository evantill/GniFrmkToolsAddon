package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 18:40
 *
 * @author: e03229
 */
public interface ListComponentIdsHandler
        <A extends ListComponentIds<I>, I extends Id, CTX extends ExecutionContext>
        extends ActionHandler<A, ListResult<I>, CTX> {
}
