package com.gni.frmk.tools.addon.handler.wm.root.port;

import com.gni.frmk.tools.addon.action.wm.root.port.GetPortDetail;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.Port.Detail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.wm.data.*;
import ev.frmk.tools.plateform.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class GetPortDetailHandler
        extends AbstractInvokeHandler<GetPortDetail, ComponentDetailResult<Detail>>
        implements ActionHandler<GetPortDetail, ComponentDetailResult<Detail>, InvokeContext> {

    public GetPortDetailHandler() {
        super("wm.server.ports:listListeners");
    }

    @Override
    protected ComponentDetailResult<Detail> parseOutput(GetPortDetail action, IData output) throws ParseOutputException {
        IDataCursor cur = output.getCursor();
        try {
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "listeners");
            if (tasksDatas != null) {
                final String idToFind = action.getId().getId();
                final String pkgToFind = action.getId().getPackageName();
                for (IData portData : tasksDatas) {
                    IDataCursor portCur = portData.getCursor();
                    try {
                        String key = IDataUtil.getString(portCur, "key");
                        String pkg = IDataUtil.getString(portCur, "pkg");
                        if (!pkgToFind.equals(pkg) || !idToFind.equals(key)) {
                            continue;
                        }
                        boolean primary = IDataUtil.getBoolean(portCur, "primary", false);
                        return new ComponentDetailResult<Detail>(new Detail(primary));
                    } finally {
                        portCur.destroy();
                    }
                }
            }
            return new ComponentDetailResult<Detail>(new Detail());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetPortDetail action) throws ParseInputException {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetPortDetail> getActionType() {
        return GetPortDetail.class;
    }

}