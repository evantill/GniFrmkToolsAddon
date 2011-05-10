package com.gni.frmk.tools.addon.handler.wm.root.port;

import com.gni.frmk.tools.addon.action.wm.root.port.ListPortListeners;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.PackageAndStringId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;
import ev.frmk.tools.plateform.api.action.ActionHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class ListPortListenersHandler
        extends AbstractInvokeHandler<ListPortListeners, ListResult<PackageAndStringId>>
        implements ActionHandler<ListPortListeners, ListResult<PackageAndStringId>, InvokeContext> {

    public ListPortListenersHandler() {
        super("wm.server.ports:listListeners");
    }

    @Override
    protected ListResult<PackageAndStringId> parseOutput(ListPortListeners action, IData output) throws ParseOutputException {
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
    protected IData prepareInput(ListPortListeners in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<ListPortListeners> getActionType() {
        return ListPortListeners.class;
    }

}