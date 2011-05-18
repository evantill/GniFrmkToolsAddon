package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.ListAdapterListenerIds;
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
public class ListAdapterListenerIdsHandler
        extends ListAdapterTypeAwareIdsHandler<ListAdapterListenerIds, ListResult<AdapterId>, AdapterId>
        implements ActionHandler<ListAdapterListenerIds, ListResult<AdapterId>, InvokeContext> {

    public ListAdapterListenerIdsHandler() {
        super("pub.art.listener:listAdapterListeners");
    }

    @Override
    public Class<ListAdapterListenerIds> getActionType() {
        return ListAdapterListenerIds.class;
    }

    @Override
    protected IData prepareInput(ListAdapterListenerIds action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getAdapterTypeFilter()}
        });
    }

    @Override
    protected ListAdapterListenerIds newFilteredAction(String adapterType) {
        return new ListAdapterListenerIds(adapterType);
    }

    @Override
    protected ListResult<AdapterId> newListResult(List<AdapterId> idList) {
        return new ListResult<AdapterId>(idList);
    }

    @Override
    protected ListResult<AdapterId> parseOutput(ListAdapterListenerIds action, IData output) {
        //TODO TESTER chez ALU sur les SAP Listeners
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "listenerDataList");
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
