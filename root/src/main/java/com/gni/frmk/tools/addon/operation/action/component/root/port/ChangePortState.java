package com.gni.frmk.tools.addon.operation.action.component.root.port;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 20:04
 *
 * @author: e03229
 */
public class ChangePortState extends ChangeComponentState<PackageAndStringId, ActivableState> {
    public ChangePortState(ActivableState newComponentState, Port component) {
        super(newComponentState, component);
    }
}
