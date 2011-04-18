package com.gni.frmk.tools.addon.handler.wm.jms.alias;

import com.gni.frmk.tools.addon.action.wm.jms.alias.GetJmsAliasReport;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.ImmutableJmsAlias.MutableJmsAlias;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState.ConnectableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.result.ListResult;
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
public class GetJmsAliasReportHandler extends AbstractInvokeHandler<GetJmsAliasReport, ListResult<MutableJmsAlias>>
        implements ActionHandler<GetJmsAliasReport, ListResult<MutableJmsAlias>, InvokeContext> {

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
    protected ListResult<MutableJmsAlias> parseOutput(GetJmsAliasReport action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, MutableJmsAlias> values = Maps.newHashMap();
            for (MutableJmsAlias value : action.getCollection()) {
                values.put(value.getComponentId().asString(), value);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "aliasDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String aliasName = IDataUtil.getString(curLoop, "aliasName");
                        MutableJmsAlias value = values.get(aliasName);
                        if (value == null && action.isUpdate()) {
                            continue;
                        } else if (value == null && !action.isUpdate()) {
                            value = new MutableJmsAlias();
                            value.setName(aliasName);
                            value.setDescription(IDataUtil.getString(curLoop, "description"));
                        }
                        ConnectableState state = parseConnectableState(IDataUtil.getIData(curLoop, "trigger"));
                        value.setState(state);
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<MutableJmsAlias>(values.values());
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
