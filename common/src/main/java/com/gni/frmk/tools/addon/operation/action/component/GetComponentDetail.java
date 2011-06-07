package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:31
 *
 * @author: e03229
 */
public abstract class GetComponentDetail
        <T extends ComponentType<T, ?, I, ?, D>,
                I extends ComponentId<I>,
                D extends ComponentDetail<D>>
        extends IdTypeAwareAction<T,I>
        implements Action<SingleResult<D>> {

    protected GetComponentDetail(T type, I id) {
        super(type, id);
    }
}
