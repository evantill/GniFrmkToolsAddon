package com.gni.frmk.tools.addon.handler.wm.root.port;

import com.gni.frmk.tools.addon.action.wm.root.port.ListPortListeners;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.ImmutablePort.MutablePort;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class ListPortListenersHandler extends AbstractInvokeHandler<ListPortListeners, ListResult<MutablePort>>
        implements ActionHandler<ListPortListeners, ListResult<MutablePort>, InvokeContext> {

    public ListPortListenersHandler() {
        super("wm.server.ports:listListeners");
    }

    @Override
    protected ListResult<MutablePort> parseOutput(ListPortListeners action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, MutablePort> values = Maps.newHashMap();
            for (MutablePort value : action.getCollection()) {
                values.put(value.getComponentId().asString(), value);
            }
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "listeners");
            if (tasksDatas != null) {
                for (IData portData : tasksDatas) {
                    IDataCursor portCur = portData.getCursor();
                    try {
                        String key = IDataUtil.getString(portCur, "key");
                        MutablePort value = values.get(key);
                        if (value == null && action.isUpdate()) {
                            continue;
                        } else if (value == null && !action.isUpdate()) {
                            value = new MutablePort();
                            value.setPrimary(IDataUtil.getBoolean(portCur, "primary", false));
                            value.setPackageName(IDataUtil.getString(portCur, "pkg"));
                        }
                        value.setState(defineState(portCur));
                        if (!value.isPrimary()) {
                            values.put(value.getComponentId().asString(), value);
                        }
                    } finally {
                        portCur.destroy();
                    }
                }
            }
            return new ListResult<MutablePort>(values.values());
        } finally {
            cur.destroy();
        }
    }

    private ActivableState defineState(IDataCursor curDoc) {
        EnableStatus enabled = EnableStatus.fromBooleanString(IDataUtil.getString(curDoc, "enabled"));
        ActivableStatus activable = ActivableStatus.fromBooleanString(IDataUtil.getString(curDoc, "suspended"))
                                                   .invert();
        return new ActivableState(enabled, activable);
    }

    @Override
    protected IData prepareInput(ListPortListeners in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<ListPortListeners> getActionType() {
        return ListPortListeners.class;
    }

}