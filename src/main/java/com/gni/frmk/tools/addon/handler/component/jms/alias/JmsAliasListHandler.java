package com.gni.frmk.tools.addon.handler.component.jms.alias;

import com.gni.frmk.tools.addon.action.component.jms.alias.GetJmsAliasDetail;
import com.gni.frmk.tools.addon.action.component.jms.alias.GetJmsAliasState;
import com.gni.frmk.tools.addon.action.component.jms.alias.JmsAliasIdList;
import com.gni.frmk.tools.addon.action.component.jms.alias.JmsAliasList;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.component.art.AbstractComponentListHandler;
import com.gni.frmk.tools.addon.model.Component.Type;
import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 17:59
 *
 * @author: e03229
 */
public class JmsAliasListHandler
        extends AbstractComponentListHandler<StringId, JmsAliasDetail, ConnectableState, JmsAlias, JmsAliasList, InvokeContext>
        implements ActionHandler<JmsAliasList, ListResult<JmsAlias>, InvokeContext> {

    @Override
    public Class<JmsAliasList> getActionType() {
        return JmsAliasList.class;
    }

    @Override
    protected Action<ListResult<StringId>> newListIdAction() {
        return new JmsAliasIdList();
    }

    @Override
    protected Action<ComponentDetailResult<JmsAliasDetail>> newGetComponentDetailAction(StringId id) {
        return new GetJmsAliasDetail(id);
    }

    @Override
    protected Action<ComponentStateResult<ConnectableState>> newGetComponentStateAction(StringId id) {
        return new GetJmsAliasState(id);
    }

    @Override
    protected JmsAlias newComponent(StringId id, JmsAliasDetail detail, ConnectableState state) throws DispatchException {
        JmsAlias component = new JmsAlias();
        component.setType(Type.JMS_ALIAS);
        component.setId(id);
        component.setDetail(detail);
        component.setCurrentState(state);
        return component;
    }
}
