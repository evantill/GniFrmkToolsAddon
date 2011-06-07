package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 13:20
 *
 * @author: e03229
 */
public abstract class ChangeComponentState
        <T extends ComponentType<T, ?, I, S, ?>,
                I extends ComponentId<I>,
                S extends ComponentState<S>>
        extends IdTypeAwareAction<T, I>
        implements Action<SingleResult<Boolean>> {

    private final S newComponentState;
    private final S oldComponentState;

    protected ChangeComponentState(T type, I id, S newComponentState, S oldComponentState) {
        super(type, id);
        this.newComponentState = newComponentState;
        this.oldComponentState = oldComponentState;
    }

    protected <C extends Component<?, ? extends T, I, S, ?>> ChangeComponentState(C component, S newComponentState) {
        super(component.getType(), component.getId());
        this.newComponentState = newComponentState;
        this.oldComponentState = component.getCurrentState();
    }

    public S getNewComponentState() {
        return newComponentState;
    }

    public S getOldComponentState() {
        return oldComponentState;
    }
}
