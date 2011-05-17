package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.GetJmsAlias;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.JmsAliasFactory;
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

    public GetJmsAliasHandler() {
        super(new JmsAliasFactory());
    }
}
