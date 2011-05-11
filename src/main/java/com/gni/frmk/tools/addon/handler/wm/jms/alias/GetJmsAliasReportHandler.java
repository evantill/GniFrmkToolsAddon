package com.gni.frmk.tools.addon.handler.wm.jms.alias;

import com.gni.frmk.tools.addon.action.wm.jms.alias.GetJmsAliasReport;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
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
public class GetJmsAliasReportHandler
        extends AbstractInvokeHandler<GetJmsAliasReport, ListResult<StringId>>
        implements ActionHandler<GetJmsAliasReport, ListResult<StringId>, InvokeContext> {

    public GetJmsAliasReportHandler() {
        super("wm.server.jms:getConnectionAliasReport");
    }

    @Override
    protected ListResult<StringId> parseOutput(GetJmsAliasReport action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            List<StringId> result = Lists.newArrayList();
            IData[] dataList = IDataUtil.getIDataArray(cur, "aliasDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String aliasName = IDataUtil.getString(curLoop, "aliasName");
                        result.add(new StringId(aliasName));
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
    protected IData prepareInput(GetJmsAliasReport in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetJmsAliasReport> getActionType() {
        return GetJmsAliasReport.class;
    }
}
