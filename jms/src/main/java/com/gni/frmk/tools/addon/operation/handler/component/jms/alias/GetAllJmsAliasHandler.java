package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetAllJmsAlias;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.JmsAliasFactory;
import com.gni.frmk.tools.addon.operation.handler.component.GetAllComponentsHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 18:58
 *
 * @author: e03229
 */
public class GetAllJmsAliasHandler
        extends GetAllComponentsHandler<StringId, JmsAliasDetail, ConnectableState, JmsAlias, GetAllJmsAlias, InvokeContext> {

    public GetAllJmsAliasHandler() {
        super(new JmsAliasFactory());
    }

    @Override
    public Class<? extends GetAllJmsAlias> getActionType() {
        return GetAllJmsAlias.class;
    }
}
