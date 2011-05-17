package com.gni.frmk.tools.addon;

import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 13:56
 *
 * @author: e03229
 */
public interface ComponentRepository<T extends Component<I, S, D>, I extends Component.Id, S extends Component.State, D extends Component.Detail> {
    Type getType();

    ListId<I> getListIdAction();

    GetComponentDetail<D,I> getDetailAction(I id);

    StateAction<I,S> getStateAction(I id);

    public static class ListId<I extends Id>
            implements Action<ListResult<I>> {
    }

    public static class DetailAction<I extends Id, D extends Detail>
            implements Action<SingleResult<D>> {
        private final I id;

        public DetailAction(I id) {
            this.id = id;
        }

        public I getId() {
            return id;
        }
    }

    public static class StateAction<I extends Id,S extends State>
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
