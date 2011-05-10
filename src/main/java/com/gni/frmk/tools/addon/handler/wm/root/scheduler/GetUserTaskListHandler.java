package com.gni.frmk.tools.addon.handler.wm.root.scheduler;

import com.gni.frmk.tools.addon.action.wm.root.scheduler.GetUserTaskList;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.StringId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;
import ev.frmk.tools.plateform.api.action.ActionHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class GetUserTaskListHandler
        extends AbstractInvokeHandler<GetUserTaskList, ListResult<StringId>>
        implements ActionHandler<GetUserTaskList, ListResult<StringId>, InvokeContext> {

    public GetUserTaskListHandler() {
        super("wm.server.schedule:getUserTaskList");
    }

    @Override
    protected ListResult<StringId> parseOutput(GetUserTaskList action, IData output) {
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
    protected IData prepareInput(GetUserTaskList in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetUserTaskList> getActionType() {
        return GetUserTaskList.class;
    }

}