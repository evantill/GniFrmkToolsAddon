package com.gni.frmk.tools.addon.operation.handler.component.oldies;

import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
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
        <A extends GetComponentDetail<T,I,D>,
                T extends ComponentType<T,?,I,?,D>,
                I extends ComponentId<I>, D extends ComponentDetail<D>, CTX extends ExecutionContext>
        extends ActionHandler<A, SingleResult<D>, CTX> {
}
