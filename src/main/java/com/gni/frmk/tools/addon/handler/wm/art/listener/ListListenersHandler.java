package com.gni.frmk.tools.addon.handler.wm.art.listener;

import com.gni.frmk.tools.addon.action.wm.art.listener.ListListeners;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ListListenersHandler
        extends AbstractInvokeHandler<ListListeners, ListResult<AdapterId>>
        implements ActionHandler<ListListeners, ListResult<AdapterId>, InvokeContext> {

    public ListListenersHandler() {
        super("pub.art.listener:listAdapterListeners");
    }

    @Override
    public Class<ListListeners> getActionType() {
        return ListListeners.class;
    }

    @Override
    protected IData prepareInput(ListListeners action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getAdapterType()}
        });
    }

    @Override
    protected ListResult<AdapterId> parseOutput(ListListeners action, IData output) {
        //TODO TESTER chez ALU sur les SAP Listeners
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "listenerDataList");
            final String adapterType = action.getAdapterType();
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
