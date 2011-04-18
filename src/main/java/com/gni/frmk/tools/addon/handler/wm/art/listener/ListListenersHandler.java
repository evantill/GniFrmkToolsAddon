package com.gni.frmk.tools.addon.handler.wm.art.listener;

import com.gni.frmk.tools.addon.action.wm.art.listener.ListListeners;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.art.AdapterTypeAwareHandler;
import com.gni.frmk.tools.addon.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterListener;
import com.gni.frmk.tools.addon.model.component.AdapterListener.AdapterListenerBuilder;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
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
public class ListListenersHandler extends AdapterTypeAwareHandler<ListListeners, ListResult<AdapterListener>>
        implements ActionHandler<ListListeners, ListResult<AdapterListener>, InvokeContext> {

    public ListListenersHandler() {
        super("pub.art.listener:listAdapterListeners");
    }

    @Override
    public Class<ListListeners> getActionType() {
        return ListListeners.class;
    }

    @Override
    protected ListResult<AdapterListener> parseOutput(ListListeners action, IData output) {
        //TODO TESTER chez ALU sur les SAP Listeners
        IDataCursor cur = output.getCursor();
        try {
            Map<String, AdapterListener> values = Maps.newHashMap();
            for (AdapterListener listener : action.getCollection()) {
                values.put(listener.getComponentId().asString(), listener);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "listenerDataList");
            String adapterType = action.getParameter();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String listenerNodeName = IDataUtil.getString(curLoop, "notificationNodeName");
                        AdapterListener value = values.get(listenerNodeName);
                        if (value == null && action.isUpdate()) {
                            continue;
                        }
                        AdapterListenerBuilder builder = AdapterListener.builder();
                        if (action.isUpdate()) {
                            builder.from(value);
                        } else {
                            builder.name(listenerNodeName)
                                   .adapterType(adapterType)
                                   .packageName(IDataUtil.getString(curLoop, "packageName"));
                        }
                        ActivableState state = defineState(IDataUtil.getString(curLoop, "notificationEnabled"));
                        value = builder.defineState(state).build();
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<AdapterListener>(values.values());
        } finally {
            cur.destroy();
        }

    }
}
