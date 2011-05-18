package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.ChangeJmsAliasState;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.DisableJmsAlias;
import com.gni.frmk.tools.addon.operation.action.component.jms.alias.EnableJmsAlias;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 13:28
 *
 * @author: e03229
 */
public class ChangeJmsAliasStateHandler
        extends ChangeComponentStateHandler<ChangeJmsAliasState, StringId, ConnectableState, ExecutionContext> {

    @Override
    public Class<? extends ChangeJmsAliasState> getActionType() {
        return ChangeJmsAliasState.class;
    }

    @Override
    protected List<Action<?>> defineActions(ConnectableState oldState, ConnectableState newState, StringId id) {
        switch (newState.getEnabled()) {
            default:
            case UNKNOWN:
                return NO_ACTION;
            case ENABLED:
                return singleAction(new EnableJmsAlias(id));
            case DISABLED:
                return singleAction(new DisableJmsAlias(id));
        }
    }
}
