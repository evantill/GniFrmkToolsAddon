package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetJmsAlias;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetJmsAliasDetail;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetJmsAliasState;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 18:52
 *
 * @author: e03229
 */
public class GetJmsAliasHandler
        extends GetComponentHandler<StringId, JmsAliasDetail, ConnectableState, JmsAlias, GetJmsAlias, InvokeContext> {

    @Override
    public Class<? extends GetJmsAlias> getActionType() {
        return GetJmsAlias.class;
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
