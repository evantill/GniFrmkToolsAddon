package com.gni.frmk.tools.addon.handler.component.jms.alias;

import com.gni.frmk.tools.addon.action.component.jms.alias.JmsAliasIdList;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.result.ListResult;
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
public class JmsAliasIdListHandler
        extends AbstractInvokeHandler<JmsAliasIdList, ListResult<StringId>>
        implements ActionHandler<JmsAliasIdList, ListResult<StringId>, InvokeContext> {

    public JmsAliasIdListHandler() {
        super("wm.server.jms:getConnectionAliasReport");
    }

    @Override
    protected ListResult<StringId> parseOutput(JmsAliasIdList action, IData output) {
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
    protected IData prepareInput(JmsAliasIdList in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<JmsAliasIdList> getActionType() {
        return JmsAliasIdList.class;
    }
}
