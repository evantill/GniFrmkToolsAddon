package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.operation.action.component.art.listener.GetListenerDetail;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener.AdapterListenerDetail;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:41
 *
 * @author: e03229
 */
public class GetListenerDetailHandler
        extends AbstractInvokeHandler<GetListenerDetail, ComponentDetailResult<AdapterListenerDetail>>
        implements ActionHandler<GetListenerDetail, ComponentDetailResult<AdapterListenerDetail>, InvokeContext> {


    public GetListenerDetailHandler() {
        super("pub.art.listener:listAdapterListeners");
    }

    @Override
    public Class<GetListenerDetail> getActionType() {
        return GetListenerDetail.class;
    }

    @Override
    protected ComponentDetailResult<AdapterListenerDetail> parseOutput(GetListenerDetail action, IData output) {
        //TODO TESTER chez ALU sur les SAP Listeners
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "listenerDataList");
            final String componentIdToFind = action.getId().getName();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String listenerNodeName = IDataUtil.getString(curLoop, "notificationNodeName");
                        if (!componentIdToFind.equals(listenerNodeName)) {
                            continue;
                        }
                        String packageName = IDataUtil.getString(curLoop, "packageName");
                        return new ComponentDetailResult<AdapterListenerDetail>(new AdapterListenerDetail(packageName));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ComponentDetailResult<AdapterListenerDetail>(new AdapterListenerDetail());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetListenerDetail action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }
}