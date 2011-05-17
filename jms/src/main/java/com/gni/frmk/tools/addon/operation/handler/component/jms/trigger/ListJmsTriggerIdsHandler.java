package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.ListJmsTriggerIds;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:37
 *
 * @author: e03229
 */
public class ListJmsTriggerIdsHandler
        extends AbstractInvokeHandler<ListJmsTriggerIds, ListResult<StringId>>
        implements ListComponentIdsHandler<ListJmsTriggerIds, StringId, InvokeContext> {

    public ListJmsTriggerIdsHandler() {
        super("wm.server.jms:getTriggerReport");
    }

    @Override
    protected ListResult<StringId> parseOutput(ListJmsTriggerIds action, IData output) {
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
    protected IData prepareInput(ListJmsTriggerIds in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<ListJmsTriggerIds> getActionType() {
        return ListJmsTriggerIds.class;
    }
}
