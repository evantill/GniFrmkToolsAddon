package com.gni.frmk.tools.addon.operation.handler.component.root.port;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.operation.action.component.root.port.ChangePortState;
import com.gni.frmk.tools.addon.operation.action.component.root.port.DisablePortListener;
import com.gni.frmk.tools.addon.operation.action.component.root.port.EnablePortListener;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 20:04
 *
 * @author: e03229
 */
public class ChangePortStateHandler extends ChangeComponentStateHandler<ChangePortState, PackageAndStringId, ActivableState, InvokeContext> {
    @Override
    protected List<Action<?>> defineActions(ActivableState oldState, ActivableState newState, PackageAndStringId id) {
        EnableStatus oldEnabled = oldState.getEnabled();
        EnableStatus newEnabled = newState.getEnabled();
        if (EnableStatus.isDisabling(oldEnabled, newEnabled)) {
            return singleAction(new EnablePortListener(id));
        } else if (EnableStatus.isEnabling(oldEnabled, newEnabled)) {
            return singleAction(new DisablePortListener(id));
        }
        return NO_ACTION;
    }

    @Override
    public Class<? extends ChangePortState> getActionType() {
        return ChangePortState.class;
    }
}
