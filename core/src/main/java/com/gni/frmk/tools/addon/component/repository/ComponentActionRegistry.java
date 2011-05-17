package com.gni.frmk.tools.addon.component.repository;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.operation.action.component.GetAllComponents;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.model.component.Component.Type;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:46
 *
 * @author: e03229
 */
public interface ComponentActionRegistry<C extends Component<I, S, D>, I extends Id, S extends State, D extends Detail> {

    Type getType();

    GetComponentDetail<D, I> getDetailAction(I id);

    GetComponentState<S, I> getStateAction(I id);

    ListComponentIds<I> getListIdsAction();

    GetComponent<C, I> getComponentAction(I id);

    GetAllComponents<C> getAllComponentsAction(I id);


}
