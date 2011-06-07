package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:37
 *
 * @author: e03229
 */
public abstract class GetComponent
        <T extends ComponentType<T, C, I, ?, ?>,
                C extends Component<C, T, I, ?, ?>,
                I extends ComponentId<I>>
        extends IdTypeAwareAction<T, I>
        implements Action<SingleResult<C>> {

    protected GetComponent(T type, I id) {
        super(type, id);
    }
}
