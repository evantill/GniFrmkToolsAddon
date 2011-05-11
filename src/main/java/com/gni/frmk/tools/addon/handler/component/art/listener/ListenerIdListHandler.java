package com.gni.frmk.tools.addon.handler.component.art.listener;

import com.gni.frmk.tools.addon.action.component.art.listener.ListenerIdList;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.handler.component.art.AdapterTypeAwareHandler;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.result.ListResult;
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
public class ListenerIdListHandler
        extends AdapterTypeAwareHandler<ListenerIdList, ListResult<AdapterId>, AdapterId>
        implements ActionHandler<ListenerIdList, ListResult<AdapterId>, InvokeContext> {

    public ListenerIdListHandler() {
        super("pub.art.listener:listAdapterListeners");
    }

    @Override
    public Class<ListenerIdList> getActionType() {
        return ListenerIdList.class;
    }

    @Override
    protected IData prepareInput(ListenerIdList action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getAdapterTypeFilter()}
        });
    }

    @Override
    protected ListenerIdList newFilteredAction(String adapterType) {
        return new ListenerIdList(adapterType);
    }

    @Override
    protected ListResult<AdapterId> newListResult(List<AdapterId> idList) {
        return new ListResult<AdapterId>(idList);
    }

    @Override
    protected ListResult<AdapterId> parseOutput(ListenerIdList action, IData output) {
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
