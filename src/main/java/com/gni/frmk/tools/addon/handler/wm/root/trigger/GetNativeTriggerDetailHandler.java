package com.gni.frmk.tools.addon.handler.wm.root.trigger;

import com.gni.frmk.tools.addon.action.wm.root.trigger.GetNativeTriggerDetail;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.NoDetail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import ev.frmk.tools.plateform.api.action.ActionException;
import ev.frmk.tools.plateform.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:37
 *
 * @author: e03229
 */
public class GetNativeTriggerDetailHandler
        implements ActionHandler<GetNativeTriggerDetail, ComponentDetailResult<NoDetail>, InvokeContext> {

    @Override
    public ComponentDetailResult<NoDetail> execute(GetNativeTriggerDetail action, InvokeContext context) throws ActionException {
        return new ComponentDetailResult<NoDetail>(NoDetail.newInstance());
    }

    @Override
    public Class<GetNativeTriggerDetail> getActionType() {
        return GetNativeTriggerDetail.class;
    }

}