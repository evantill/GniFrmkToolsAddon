package com.gni.frmk.tools.addon.command.handler.wm.root.port;

import com.gni.frmk.tools.addon.command.action.wm.root.port.ListPortListeners;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.Port;
import com.gni.frmk.tools.addon.model.component.Port.PortBuilder;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
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
public class ListPortListenersHandler extends AbstractInvokeHandler<ListPortListeners, ListResult<Port>>
        implements ActionHandler<ListPortListeners, ListResult<Port>, InvokeContext> {

    public ListPortListenersHandler() {
        super("wm.server.ports:listListeners");
    }

    @Override
    protected ListResult<Port> parseOutput(ListPortListeners action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, Port> values = Maps.newHashMap();
            for (Port value : action.getCollection()) {
                values.put(value.getComponentId().asString(), value);
            }
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "listeners");
            if (tasksDatas != null) {
                for (IData portData : tasksDatas) {
                    IDataCursor portCur = portData.getCursor();
                    try {
                        String key = IDataUtil.getString(portCur, "key");
                        Port value = values.get(key);
                        if (value == null && action.isUpdate()) {
                            continue;
                        }
                        PortBuilder builder = Port.builder();
                        if (action.isUpdate()) {
                            builder.from(value);
                        } else {
                            builder.key(key)
                                   .primary(IDataUtil.getBoolean(portCur, "primary", false))
                                   .packageName(IDataUtil.getString(portCur, "pkg"));
                        }
                        value = builder.defineState(defineState(portCur)).build();
                        if (!value.isPrimary()) {
                            values.put(value.getComponentId().asString(), value);
                        }
                    } finally {
                        portCur.destroy();
                    }
                }
            }
            return new ListResult<Port>(values.values());
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