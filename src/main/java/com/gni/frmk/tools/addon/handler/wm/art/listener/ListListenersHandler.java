package com.gni.frmk.tools.addon.handler.wm.art.listener;

import com.gni.frmk.tools.addon.action.wm.art.listener.ListListeners;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.art.AdapterTypeAwareHandler;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterListener.MutableAdapterListener;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

import static com.gni.frmk.tools.addon.handler.wm.art.ListenerNotificationUtils.defineState;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ListListenersHandler extends AdapterTypeAwareHandler<ListListeners, ListResult<MutableAdapterListener>>
        implements ActionHandler<ListListeners, ListResult<MutableAdapterListener>, InvokeContext> {

    public ListListenersHandler() {
        super("pub.art.listener:listAdapterListeners");
    }

    @Override
    public Class<ListListeners> getActionType() {
        return ListListeners.class;
    }

    @Override
    protected ListResult<MutableAdapterListener> parseOutput(ListListeners action, IData output) {
        //TODO TESTER chez ALU sur les SAP Listeners
        IDataCursor cur = output.getCursor();
        try {
            Map<String, MutableAdapterListener> values = Maps.newHashMap();
            for (MutableAdapterListener listener : action.getCollection()) {
                values.put(listener.getComponentId().asString(), listener);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "listenerDataList");
            String adapterType = action.getParameter();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String listenerNodeName = IDataUtil.getString(curLoop, "notificationNodeName");
                        MutableAdapterListener value = values.get(listenerNodeName);
                        if (value == null && action.isUpdate()) {
                            continue;
                        } else if (value == null && !action.isUpdate()) {
                            value = new MutableAdapterListener();
                            value.setAdapterType(adapterType);
                            value.setPackageName(IDataUtil.getString(curLoop, "packageName"));
                        }
                        ActivableState state = defineState(IDataUtil.getString(curLoop, "notificationEnabled"));
                        value.setState(state);
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<MutableAdapterListener>(values.values());
        } finally {
            cur.destroy();
        }

    }
}
