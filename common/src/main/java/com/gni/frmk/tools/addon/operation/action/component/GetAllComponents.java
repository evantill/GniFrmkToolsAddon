package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:38
 *
 * @author: e03229
 */
public abstract class GetAllComponents
        <T extends ComponentType<T, C, I, ?, ?>,
                C extends Component<C, T, I, ?, ?>,
                I extends ComponentId<I>>
        extends IdTypeAwareAction<T, I>
        implements Action<ListResult<C>> {
    protected GetAllComponents(T type, I id) {
        super(type, id);
    }
}
