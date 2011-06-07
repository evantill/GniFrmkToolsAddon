package com.gni.frmk.tools.addon.operation.handler.component.root.port.oldies;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.PortDetail;
import com.gni.frmk.tools.addon.operation.action.component.root.port.GetPortDetail;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.GetComponentDetailHandler;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class GetPortDetailHandler
        extends AbstractInvokeHandler<GetPortDetail, SingleResult<PortDetail>>
        implements GetComponentDetailHandler<GetPortDetail, PackageAndStringId, PortDetail, InvokeContext> {

    public GetPortDetailHandler() {
        super("wm.server.ports:listListeners");
    }

    @Override
    protected SingleResult<PortDetail> parseOutput(GetPortDetail action, IData output) throws ParseOutputException {
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
                        return new SingleResult<PortDetail>(new PortDetail(primary));
                    } finally {
                        portCur.destroy();
                    }
                }
            }
            return new SingleResult<PortDetail>(new PortDetail());
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