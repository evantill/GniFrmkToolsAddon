package com.gni.frmk.tools.addon;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 13:56
 *
 * @author: e03229
 */
public interface ComponentRepository
        <C extends Component<C, T, I, S, D>,
                T extends ComponentType<T, C, I, S, D>,
                I extends ComponentId<I>,
                S extends ComponentState<S>,
                D extends ComponentDetail<D>> {
    ComponentType getType();

    ListId<I> getListIdAction();

    GetComponentDetail<I, D> getDetailAction(I id);

    StateAction<I, S> getStateAction(I id);

    public static class ListId<I extends ComponentId>
            implements Action<ListResult<I>> {
    }

    public static class DetailAction<I extends ComponentId, D extends ComponentDetail>
            implements Action<SingleResult<D>> {
        private final I id;

        public DetailAction(I id) {
            this.id = id;
        }

        public I getId() {
            return id;
        }
    }

    public static class StateAction<I extends ComponentId, S extends ComponentState>
            implements Action<SingleResult<S>> {
        private final I id;

        public StateAction(I id) {
            this.id = id;
        }

        public I getId() {
            return id;
        }
    }
}
