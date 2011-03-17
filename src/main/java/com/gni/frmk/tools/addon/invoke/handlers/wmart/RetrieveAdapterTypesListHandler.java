package com.gni.frmk.tools.addon.invoke.handlers.wmart;

import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.results.SetResult;
import com.gni.frmk.tools.addon.invoke.actions.wmart.RetrieveAdapterTypesList;
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
public class RetrieveAdapterTypesListHandler extends AbstractInvokeHandler<RetrieveAdapterTypesList, SetResult<String>>
        implements ActionHandler<RetrieveAdapterTypesList, SetResult<String>, InvokeContext> {

    public RetrieveAdapterTypesListHandler() {
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
    protected SetResult<String> parseOutput(RetrieveAdapterTypesList action, IData output) {
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
