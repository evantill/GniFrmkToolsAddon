package com.gni.frmk.tools.addon.operation.handler.component.root.trigger.oldies;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.root.trigger.GetNativeTriggerDetail;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.GetComponentDetailHandler;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:37
 *
 * @author: e03229
 */
public class GetNativeTriggerDetailHandler
        implements GetComponentDetailHandler<GetNativeTriggerDetail, StringId, NoDetail, InvokeContext> {

    @Override
    public SingleResult<NoDetail> execute(GetNativeTriggerDetail action, InvokeContext context) throws ActionException {
        return new SingleResult<NoDetail>(NoDetail.newInstance());
    }

    @Override
    public Class<GetNativeTriggerDetail> getActionType() {
        return GetNativeTriggerDetail.class;
    }

}
