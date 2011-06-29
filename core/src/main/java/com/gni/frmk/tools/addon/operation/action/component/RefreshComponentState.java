package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 29/06/11
 * Time: 16:38
 *
 * @author: e03229
 */
public class RefreshComponentState<C extends Component<C, ?, I, S, ?>,
        I extends ComponentId<I>,
        S extends ComponentState<S>>
        implements Action<SingleResult<C>> {
    private final C component;

    public RefreshComponentState(C component) {
        this.component = component;
    }

    public C getComponent() {
        return component;
    }

    public static <C extends Component<C, ?, I, S, ?>, I extends ComponentId<I>, S extends ComponentState<S>>
    RefreshComponentState<C, I, S> build(C component) {
        return new RefreshComponentState<C, I, S>(component);
    }
}
