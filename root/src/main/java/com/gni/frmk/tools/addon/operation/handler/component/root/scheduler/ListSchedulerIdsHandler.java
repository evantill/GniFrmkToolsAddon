package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.ListSchedulerIds;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class ListSchedulerIdsHandler
        extends AbstractInvokeHandler<ListSchedulerIds, ListResult<StringId>>
        implements ActionHandler<ListSchedulerIds, ListResult<StringId>, InvokeContext> {

    public ListSchedulerIdsHandler() {
        super("wm.server.schedule:getUserTaskList");
    }

    @Override
    protected ListResult<StringId> parseOutput(ListSchedulerIds action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "tasks");
            List<StringId> result = Lists.newArrayList();
            if (tasksDatas != null) {
                for (IData tasks : tasksDatas) {
                    IDataCursor curDoc = tasks.getCursor();
                    try {
                        String oid = IDataUtil.getString(curDoc, "oid");
                        result.add(new StringId(oid));
                    } finally {
                        curDoc.destroy();
                    }
                }
            }
            return new ListResult<StringId>(result);
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(ListSchedulerIds in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<ListSchedulerIds> getActionType() {
        return ListSchedulerIds.class;
    }

}