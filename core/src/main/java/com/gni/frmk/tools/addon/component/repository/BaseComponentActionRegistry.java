package com.gni.frmk.tools.addon.component.repository;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.operation.action.component.GetAllComponents;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 15:01
 *
 * @author: e03229
 */
public abstract class BaseComponentActionRegistry<C extends Component<I, S, D>, I extends Id, S extends State, D extends Detail>
    implements ComponentActionRegistry<C,I,S,D>{

    @Override
    public GetComponent<C,I> getComponentAction(I id) {
        return new GetComponent<C, I>(getType(),id);
    }

    @Override
    public GetAllComponents<C> getAllComponentsAction(I id) {
        return new GetAllComponents<C>(getType());
    }
}
