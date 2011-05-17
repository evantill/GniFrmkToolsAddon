package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetAllJmsAlias;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetJmsAliasDetail;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetJmsAliasState;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.ListJmsAliasIds;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.handler.component.GetAllComponentsHandler;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 18:58
 *
 * @author: e03229
 */
public class GetAllJmsAliasHandler
        extends GetAllComponentsHandler<StringId, JmsAliasDetail, ConnectableState, JmsAlias, GetAllJmsAlias, InvokeContext> {

    @Override
    protected Action<ListResult<StringId>> newListIdAction() {
        return new ListJmsAliasIds();
    }

    @Override
    public Class<? extends GetAllJmsAlias> getActionType() {
        return GetAllJmsAlias.class;
    }

    @Override
    protected GetComponentDetail<JmsAliasDetail, StringId> newGetComponentDetailAction(StringId id) {
        return new GetJmsAliasDetail(id);
    }

    @Override
    protected GetComponentState<ConnectableState, StringId> newGetComponentStateAction(StringId id) {
        return new GetJmsAliasState(id);
    }

    @Override
    protected JmsAlias newComponent(StringId id, JmsAliasDetail detail, ConnectableState state) throws DispatchException {
        JmsAlias result = new JmsAlias();
        result.setType(Type.JMS_ALIAS);
        result.setId(id);
        result.setDetail(detail);
        result.setCurrentState(state);
        return result;
    }
}
