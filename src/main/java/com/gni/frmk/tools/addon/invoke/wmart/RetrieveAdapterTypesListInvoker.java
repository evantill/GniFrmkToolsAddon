package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.SetResult;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.google.common.collect.Sets;
import com.wm.data.*;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:24
 *
 * @author: e03229
 */
 class RetrieveAdapterTypesListInvoker extends AbstractInvoker<RetrieveAdapterTypesList, SetResult<String>>
        implements ActionHandler<RetrieveAdapterTypesList, SetResult<String>, InvokeContext> {

    public RetrieveAdapterTypesListInvoker() {
        super("wm.art.admin:retrieveAdapterTypesList");
    }

    @Override
    public Class<RetrieveAdapterTypesList> getActionType() {
        return RetrieveAdapterTypesList.class;
    }

    @Override
    protected IData prepareInput(RetrieveAdapterTypesList in) {
        return EMPTY_INPUT;
    }

    @Override
    protected SetResult<String> parseOutput(IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Set<String> values = Sets.newHashSet();
            IData[] dataList = IDataUtil.getIDataArray(cur, "adapterTypes");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        values.add(IDataUtil.getString(curLoop, "adapterName"));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new SetResult<String>(values);
        } finally {
            cur.destroy();
        }
    }
}
