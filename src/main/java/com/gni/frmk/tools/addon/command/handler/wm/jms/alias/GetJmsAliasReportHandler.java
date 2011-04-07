package com.gni.frmk.tools.addon.command.handler.wm.jms.alias;

import com.gni.frmk.tools.addon.command.action.wm.jms.alias.GetJmsAliasReport;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.JmsAlias;
import com.gni.frmk.tools.addon.model.component.JmsAlias.JmsAliasBuilder;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState.ConnectableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

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
            Map<String, JmsAlias> values = Maps.newHashMap();
            for (JmsAlias value : action.getCollection()) {
                values.put(value.getComponentId().asString(), value);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "aliasDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String aliasName = IDataUtil.getString(curLoop, "aliasName");
                        JmsAlias value = values.get(aliasName);
                        if (value == null && action.isUpdate()) {
                            continue;
                        }
                        JmsAliasBuilder builder = JmsAlias.builder();
                        if (action.isUpdate()) {
                            builder.from(value);
                        } else {
                            builder.name(aliasName)
                                   .description(IDataUtil.getString(curLoop, "description"));
                        }
                        ConnectableState state = parseConnectableState(IDataUtil.getIData(curLoop, "trigger"));
                        value = builder.defineState(state).build();
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<JmsAlias>(values.values());
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
