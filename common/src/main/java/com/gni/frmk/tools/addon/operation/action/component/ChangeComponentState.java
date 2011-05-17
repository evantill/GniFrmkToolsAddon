package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 13:20
 *
 * @author: e03229
 */
public abstract class ChangeComponentState<I extends Id, S extends State>
        implements Action<SingleResult<Boolean>> {

    private final S newComponentState;
    private final S oldComponentState;
    private final I componentId;

    protected <C extends Component<I, S, ?>> ChangeComponentState(S newComponentState, C component) {
        this.newComponentState = newComponentState;
        this.oldComponentState = component.getCurrentState();
        this.componentId = component.getId();
    }

    public S getNewComponentState() {
        return newComponentState;
    }

    public S getOldComponentState() {
        return oldComponentState;
    }

    public I getComponentId() {
        return componentId;
    }
}
