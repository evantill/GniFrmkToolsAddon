package com.gni.frmk.tools.addon.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.action.component.jms.trigger.GetJmsTriggerDetail;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger.JmsTriggerDetail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 16:23
 *
 * @author: e03229
 */
public class GetJmsTriggerDetailHandler
        extends AbstractInvokeHandler<GetJmsTriggerDetail, ComponentDetailResult<JmsTriggerDetail>>
        implements ActionHandler<GetJmsTriggerDetail, ComponentDetailResult<JmsTriggerDetail>, InvokeContext> {

    public GetJmsTriggerDetailHandler() {
        super("wm.server.jms:getTriggerReport");
    }

    @Override
    protected ComponentDetailResult<JmsTriggerDetail> parseOutput(GetJmsTriggerDetail action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            String triggerNameToFind = action.getId().getValue();
            IData[] dataList = IDataUtil.getIDataArray(cur, "triggerDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String triggerName = IDataUtil.getString(curLoop, "node_nsName");
                        if (!triggerNameToFind.equals(triggerName)) {
                            continue;
                        }
                        String packageName = IDataUtil.getString(curLoop, "node_pkg");
                        return new ComponentDetailResult<JmsTriggerDetail>(new JmsTriggerDetail(packageName));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ComponentDetailResult<JmsTriggerDetail>(new JmsTriggerDetail());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetJmsTriggerDetail in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetJmsTriggerDetail> getActionType() {
        return GetJmsTriggerDetail.class;
    }
}