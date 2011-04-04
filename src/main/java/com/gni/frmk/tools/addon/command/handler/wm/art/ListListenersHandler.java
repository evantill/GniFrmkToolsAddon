package com.gni.frmk.tools.addon.command.handler.wm.art;

import com.gni.frmk.tools.addon.command.action.wm.art.ListListeners;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterListener;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

import static com.gni.frmk.tools.addon.command.handler.wm.art.ListenerNotificationUtils.defineState;

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
        super("pub.art.notification:listAdapterListenerNotifications");
    }

    @Override
    public Class<ListListeners> getActionType() {
        return ListListeners.class;
    }

    @Override
    protected ListResult<AdapterListener> parseOutput(ListListeners action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            List<AdapterListener> values = Lists.newArrayList();
            IData[] dataList = IDataUtil.getIDataArray(cur, "notificationDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        ActivableState state = defineState(IDataUtil.getString(curLoop, "notificationEnabled"));
                        values.add(AdapterListener.builder()
                                                  .name(IDataUtil.getString(curLoop, "notificationNodeName"))
                                                  .adapterType(IDataUtil.getString(curLoop, "name"))
                                                  .packageName(IDataUtil.getString(curLoop, "packageName"))
                                                  .defineState(state)
                                                  .build());
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<AdapterListener>(values);
        } finally {
            cur.destroy();
        }

    }
}
