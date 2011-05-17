package com.gni.frmk.tools.addon.operation.handler.component.root.trigger;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.root.trigger.ListNativeTriggerIds;
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
public class ListNativeTriggerIdsHandler
        extends AbstractInvokeHandler<ListNativeTriggerIds, ListResult<StringId>>
        implements ListComponentIdsHandler<ListNativeTriggerIds, StringId, InvokeContext> {

    public ListNativeTriggerIdsHandler() {
        super("wm.server.triggers:getTriggerReport");
    }

    @Override
    protected ListResult<StringId> parseOutput(ListNativeTriggerIds action, IData output) throws ParseOutputException {
        IDataCursor cur = output.getCursor();
        try {
            List<StringId> result = Lists.newArrayList();
            IData[] dataList = IDataUtil.getIDataArray(cur, "triggers");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String name = IDataUtil.getString(curLoop, "name");
                        result.add(new StringId(name));
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
    protected IData prepareInput(ListNativeTriggerIds in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<ListNativeTriggerIds> getActionType() {
        return ListNativeTriggerIds.class;
    }
}
