package com.gni.frmk.tools.addon.component.repository;

import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetJmsAliasDetail;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetJmsAliasState;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.ListJmsAliasIds;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 15:12
 *
 * @author: e03229
 */
public class JmsAliasRepository
        extends BaseComponentActionRegistry<JmsAlias, StringId, ConnectableState, JmsAliasDetail> {

    @Override
    public Type getType() {
        return Type.JMS_ALIAS;
    }

    @Override
    public GetComponentDetail<JmsAliasDetail, StringId> getDetailAction(StringId id) {
        return new GetJmsAliasDetail(id);
    }

    @Override
    public GetComponentState<ConnectableState, StringId> getStateAction(StringId id) {
        return new GetJmsAliasState(id);
    }

    @Override
    public ListComponentIds<StringId> getListIdsAction() {
        return new ListJmsAliasIds();
    }
}
