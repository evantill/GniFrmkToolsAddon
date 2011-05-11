package com.gni.frmk.tools.addon.handler.wm.root.trigger;

import com.gni.frmk.tools.addon.action.wm.root.trigger.GetNativeTriggerReport;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
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
public class GetNativeTriggerReportHandler
        extends AbstractInvokeHandler<GetNativeTriggerReport, ListResult<StringId>>
        implements ActionHandler<GetNativeTriggerReport, ListResult<StringId>, InvokeContext> {

    public GetNativeTriggerReportHandler() {
        super("wm.server.triggers:getTriggerReport");
    }

    @Override
    protected ListResult<StringId> parseOutput(GetNativeTriggerReport action, IData output) throws ParseOutputException {
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
    protected IData prepareInput(GetNativeTriggerReport in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetNativeTriggerReport> getActionType() {
        return GetNativeTriggerReport.class;
    }
}
