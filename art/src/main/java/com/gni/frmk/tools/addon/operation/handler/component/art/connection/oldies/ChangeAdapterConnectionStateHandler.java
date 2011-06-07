package com.gni.frmk.tools.addon.operation.handler.component.art.connection.oldies;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.oldies.ChangeAdapterConnectionState;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.oldies.DisableAdapterConnection;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.oldies.EnableAdapterConnection;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.ChangeComponentStateHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:14
 *
 * @author: e03229
 */
public class ChangeAdapterConnectionStateHandler
        extends ChangeComponentStateHandler<ChangeAdapterConnectionState, AdapterId, EnableState, InvokeContext> {

    @Override
    protected List<Action<?>> defineActions(EnableState oldState, EnableState newState, AdapterId id) {
        switch (newState.getEnabled()) {
            default:
            case UNKNOWN:
                return NO_ACTION;
            case ENABLED:
                return singleAction(new EnableAdapterConnection(id));
            case DISABLED:
                return singleAction(new DisableAdapterConnection(id));
        }
    }

    @Override
    public Class<? extends ChangeAdapterConnectionState> getActionType() {
        return ChangeAdapterConnectionState.class;
    }
}
