package com.gni.frmk.tools.addon.operation.handler.component.root.port;

import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.action.component.root.port.PortListenerIdList;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class PortListenerIdListHandler
        extends AbstractInvokeHandler<PortListenerIdList, ListResult<PackageAndStringId>>
        implements ActionHandler<PortListenerIdList, ListResult<PackageAndStringId>, InvokeContext> {

    public PortListenerIdListHandler() {
        super("wm.server.ports:listListeners");
    }

    @Override
    protected ListResult<PackageAndStringId> parseOutput(PortListenerIdList action, IData output) throws ParseOutputException {
        IDataCursor cur = output.getCursor();
        try {
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "listeners");
            List<PackageAndStringId> result = Lists.newArrayList();
            if (tasksDatas != null) {
                for (IData portData : tasksDatas) {
                    IDataCursor portCur = portData.getCursor();
                    try {
                        String key = IDataUtil.getString(portCur, "key");
                        String pkg = IDataUtil.getString(portCur, "pkg");
                        result.add(new PackageAndStringId(pkg, key));
                    } finally {
                        portCur.destroy();
                    }
                }
            }
            return new ListResult<PackageAndStringId>(result);
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(PortListenerIdList in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<PortListenerIdList> getActionType() {
        return PortListenerIdList.class;
    }

}