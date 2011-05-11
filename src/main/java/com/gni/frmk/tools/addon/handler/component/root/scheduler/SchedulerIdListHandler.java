package com.gni.frmk.tools.addon.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.action.component.root.scheduler.SchedulerIdList;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class SchedulerIdListHandler
        extends AbstractInvokeHandler<SchedulerIdList, ListResult<StringId>>
        implements ActionHandler<SchedulerIdList, ListResult<StringId>, InvokeContext> {

    public SchedulerIdListHandler() {
        super("wm.server.schedule:getUserTaskList");
    }

    @Override
    protected ListResult<StringId> parseOutput(SchedulerIdList action, IData output) {
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
    protected IData prepareInput(SchedulerIdList in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<SchedulerIdList> getActionType() {
        return SchedulerIdList.class;
    }

}