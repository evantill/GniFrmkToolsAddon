package com.gni.frmk.tools.addon.operation.action.component.jms.alias;

import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.operation.action.component.ComponentFactory;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:06
 *
 * @author: e03229
 */
public class JmsAliasFactory implements ComponentFactory<StringId, ConnectableState, JmsAliasDetail, JmsAlias> {

    public JmsAlias newComponent(StringId id, JmsAliasDetail detail, ConnectableState state) {
        JmsAlias component = new JmsAlias();
        component.setType(Type.JMS_ALIAS);
        component.setId(id);
        component.setCurrentState(state);
        component.setDetail(detail);
        return component;
    }

    @Override
    public ListComponentIds<StringId> newListComponentIdsAction() {
        return new ListJmsAliasIds();
    }

    public GetAllJmsAlias newGetAllComponentAction() {
        return new GetAllJmsAlias();
    }

    public GetJmsAlias newGetComponentAction(StringId id) {
        return new GetJmsAlias(id);
    }

    public GetJmsAliasDetail newGetComponentDetailAction(StringId id) {
        return new GetJmsAliasDetail(id);
    }

    public GetJmsAliasState newGetComponentStateAction(StringId id) {
        return new GetJmsAliasState(id);
    }
}
