package com.gni.frmk.tools.addon.handler.wm.root.scheduler;

import com.gni.frmk.tools.addon.action.wm.root.scheduler.GetSchedulerDetail;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.Scheduler.Detail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:02
 *
 * @author: e03229
 */
public class GetSchedulerDetailHandler
        extends AbstractInvokeHandler<GetSchedulerDetail, ComponentDetailResult<Detail>>
        implements ActionHandler<GetSchedulerDetail, ComponentDetailResult<Detail>, InvokeContext> {

    public GetSchedulerDetailHandler() {
        super("wm.server.schedule:getUserTaskList");
    }

    @Override
    protected ComponentDetailResult<Detail> parseOutput(GetSchedulerDetail action, IData output) throws ParseOutputException {
        IDataCursor cur = output.getCursor();
        try {
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "tasks");
            String oidToFind = action.getId().getValue();
            if (tasksDatas != null) {
                for (IData tasks : tasksDatas) {
                    IDataCursor curDoc = tasks.getCursor();
                    try {
                        String oid = IDataUtil.getString(curDoc, "oid");
                        if (!oidToFind.equals(oid)) {
                            continue;
                        }
                        String name = IDataUtil.getString(curDoc, "name");
                        String type = IDataUtil.getString(curDoc, "type");
                        String service1 = IDataUtil.getString(curDoc, "service");
                        String description = IDataUtil.getString(curDoc, "description");
                        return new ComponentDetailResult<Detail>(new Detail(type, name, service1, description));
                    } finally {
                        curDoc.destroy();
                    }
                }
            }
            return new ComponentDetailResult<Detail>(new Detail());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetSchedulerDetail action) throws ParseInputException {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetSchedulerDetail> getActionType() {
        return GetSchedulerDetail.class;
    }

}
