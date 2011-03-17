package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.configuration.components.ActivableState;
import com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.configuration.components.Port;
import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.ListPortListeners;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.results.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

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
            List<Port> values = Lists.newArrayList();
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "listeners");
            if (tasksDatas != null) {

                for (IData portData : tasksDatas) {
                    Port port = parsePort(portData);
                    if (!port.isPrimary()) {
                        values.add(port);
                    }
                }
            }
            return new ListResult<Port>(values);
        } finally {
            cur.destroy();
        }
    }

    private Port parsePort(IData data) {
        IDataCursor cur = data.getCursor();
        try {
            return Port.builder()
                       .key(IDataUtil.getString(cur, "key"))
                       .primary(IDataUtil.getBoolean(cur, "primary", false))
                       .packageName(IDataUtil.getString(cur, "pkg"))
                       .defineState(defineState(cur))
                       .build();
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