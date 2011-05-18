package com.gni.frmk.tools.addon.operation.handler.component.art;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.action.component.art.ListAdapterTypes;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.result.SetResult;
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
public class ListAdapterTypesHandler extends AbstractInvokeHandler<ListAdapterTypes, SetResult<String>>
        implements ActionHandler<ListAdapterTypes, SetResult<String>, InvokeContext> {

    public ListAdapterTypesHandler() {
        super("wm.art.admin:retrieveAdapterTypesList");
    }

    @Override
    public Class<ListAdapterTypes> getActionType() {
        return ListAdapterTypes.class;
    }

    @Override
    protected IData prepareInput(ListAdapterTypes in) {
        return EMPTY_INPUT;
    }

    @Override
    protected SetResult<String> parseOutput(ListAdapterTypes action, IData output) {
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
