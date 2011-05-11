package com.gni.frmk.tools.addon.handler.component.jms.alias;

import com.gni.frmk.tools.addon.action.component.jms.alias.GetJmsAliasDetail;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 16:34
 *
 * @author: e03229
 */
public class GetJmsAliasDetailHandler
        extends AbstractInvokeHandler<GetJmsAliasDetail, ComponentDetailResult<JmsAliasDetail>>
        implements ActionHandler<GetJmsAliasDetail, ComponentDetailResult<JmsAliasDetail>, InvokeContext> {

    public GetJmsAliasDetailHandler() {
        super("wm.server.jms:getConnectionAliasReport");
    }

    @Override
    protected ComponentDetailResult<JmsAliasDetail> parseOutput(GetJmsAliasDetail action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "aliasDataList");
            String aliasNameToFind = action.getId().getValue();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String aliasName = IDataUtil.getString(curLoop, "aliasName");
                        if (!aliasNameToFind.equals(aliasName)) {
                            continue;
                        }
                        String description = IDataUtil.getString(curLoop, "description");
                        return new ComponentDetailResult<JmsAliasDetail>(new JmsAliasDetail(description));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ComponentDetailResult<JmsAliasDetail>(new JmsAliasDetail());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetJmsAliasDetail in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetJmsAliasDetail> getActionType() {
        return GetJmsAliasDetail.class;
    }
}
