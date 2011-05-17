package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:09
 *
 * @author: e03229
 */
public interface ComponentFactory
        <I extends Id, S extends State, D extends Detail, C extends Component<I, S, D>> {

    C newComponent(I id, D detail, S state);

    ListComponentIds<I> newListComponentIdsAction();

    GetAllComponents<C> newGetAllComponentAction();

    GetComponent<C, I> newGetComponentAction(I id);

    GetComponentDetail<D, I> newGetComponentDetailAction(I id);

    GetComponentState<S, I> newGetComponentStateAction(I id);
}
