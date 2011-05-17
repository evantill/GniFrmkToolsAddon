package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.ListJmsAliasIds;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:37
 *
 * @author: e03229
 */
public class ListJmsAliasIdsHandler
        extends AbstractInvokeHandler<ListJmsAliasIds, ListResult<StringId>>
        implements ListComponentIdsHandler<ListJmsAliasIds, StringId, InvokeContext> {

    public ListJmsAliasIdsHandler() {
        super("wm.server.jms:getConnectionAliasReport");
    }

    @Override
    protected ListResult<StringId> parseOutput(ListJmsAliasIds action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            List<StringId> result = Lists.newArrayList();
            IData[] dataList = IDataUtil.getIDataArray(cur, "aliasDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String aliasName = IDataUtil.getString(curLoop, "aliasName");
                        result.add(new StringId(aliasName));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<StringId>(result);
        } finally {
            cur.destroy();
        }

    }

    @Override
    protected IData prepareInput(ListJmsAliasIds in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<ListJmsAliasIds> getActionType() {
        return ListJmsAliasIds.class;
    }
}
