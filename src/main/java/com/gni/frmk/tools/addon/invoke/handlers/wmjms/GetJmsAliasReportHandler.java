package com.gni.frmk.tools.addon.invoke.handlers.wmjms;

import com.gni.frmk.tools.addon.configuration.components.ConnectableState;
import com.gni.frmk.tools.addon.configuration.components.ConnectableState.ConnectableStatus;
import com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.configuration.components.JmsAlias;
import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmjms.GetJmsAliasReport;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.results.ListResult;
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
public class GetJmsAliasReportHandler extends AbstractInvokeHandler<GetJmsAliasReport, ListResult<JmsAlias>>
        implements ActionHandler<GetJmsAliasReport, ListResult<JmsAlias>, InvokeContext> {

    public GetJmsAliasReportHandler() {
        super("wm.server.jms:getConnectionAliasReport");
    }

    private ConnectableState parseConnectableState(IData doc) {
        IDataCursor cur = doc.getCursor();
        try {
            final EnableStatus enabled = EnableStatus.fromBoolean(IDataUtil.getBoolean(cur, "enabled", false));
            final ConnectableStatus connected = ConnectableStatus.fromBoolean(IDataUtil.getBoolean(cur, "connected", false));
            return new ConnectableState(enabled, connected);
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected ListResult<JmsAlias> parseOutput(GetJmsAliasReport action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            List<JmsAlias> values = Lists.newArrayList();
            IData[] dataList = IDataUtil.getIDataArray(cur, "aliasDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        values.add(JmsAlias.builder()
                                           .name(IDataUtil.getString(curLoop, "aliasName"))
                                           .description(IDataUtil.getString(curLoop, "description"))
                                           .defineState(parseConnectableState(IDataUtil.getIData(curLoop, "trigger")))
                                           .build());
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<JmsAlias>(values);
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
