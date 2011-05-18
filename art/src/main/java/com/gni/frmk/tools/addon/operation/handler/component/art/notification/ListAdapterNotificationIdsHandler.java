package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.ListAdapterNotificationIds;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.operation.handler.component.art.ListAdapterTypeAwareIdsHandler;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ListAdapterNotificationIdsHandler
        extends ListAdapterTypeAwareIdsHandler<ListAdapterNotificationIds, ListResult<AdapterId>, AdapterId>
        implements ActionHandler<ListAdapterNotificationIds, ListResult<AdapterId>, InvokeContext> {

    public ListAdapterNotificationIdsHandler() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    public Class<ListAdapterNotificationIds> getActionType() {
        return ListAdapterNotificationIds.class;
    }

    @Override
    protected IData prepareInput(ListAdapterNotificationIds action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getAdapterTypeFilter()}
        });
    }


    @Override
    protected ListAdapterNotificationIds newFilteredAction(String adapterType) {
        return new ListAdapterNotificationIds(adapterType);
    }

    @Override
    protected ListResult<AdapterId> newListResult(List<AdapterId> idList) {
        return new ListResult<AdapterId>(idList);
    }

    @Override
    protected ListResult<AdapterId> parseOutput(ListAdapterNotificationIds action, IData output) {
        //TODO TESTER chez ALU sur les SAP Listeners
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "notificationDataList");
            final String adapterType = action.getAdapterTypeFilter();
            final List<AdapterId> result = Lists.newArrayList();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String listenerNodeName = IDataUtil.getString(curLoop, "notificationNodeName");
                        result.add(new AdapterId(listenerNodeName, adapterType));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<AdapterId>(result);
        } finally {
            cur.destroy();
        }
    }
}
