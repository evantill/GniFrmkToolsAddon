package com.gni.frmk.tools.addon.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.action.component.jms.trigger.JmsTriggerIdList;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:37
 *
 * @author: e03229
 */
public class JmsTriggerIdListHandler
        extends AbstractInvokeHandler<JmsTriggerIdList, ListResult<StringId>>
        implements ActionHandler<JmsTriggerIdList, ListResult<StringId>, InvokeContext> {

    public JmsTriggerIdListHandler() {
        super("wm.server.jms:getTriggerReport");
    }

    @Override
    protected ListResult<StringId> parseOutput(JmsTriggerIdList action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            List<StringId> result = Lists.newArrayList();
            IData[] dataList = IDataUtil.getIDataArray(cur, "triggerDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String triggerName = IDataUtil.getString(curLoop, "node_nsName");
                        result.add(new StringId(triggerName));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<StringId>(result);
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(JmsTriggerIdList in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<JmsTriggerIdList> getActionType() {
        return JmsTriggerIdList.class;
    }
}
