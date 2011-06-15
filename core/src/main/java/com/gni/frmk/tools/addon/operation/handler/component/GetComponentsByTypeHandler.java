package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.GetAllComponents;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 13:17
 *
 * @author: e03229
 */
public class GetComponentsByTypeHandler
        implements ActionHandler<GetAllComponents<?, ?, ?, ?, ?>, ListResult<? extends Component<?, ?, ?, ?, ?>>, InvokeContext> {

    @Override
    public Class<?> getActionType() {
        return GetAllComponents.class;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public ListResult<Component> execute(GetAllComponents action, InvokeContext context) throws ActionException {
        return executeTypeSafe(action, context);
    }

    public <T extends ComponentType<T, C, I, S, D>,
            C extends Component<C, T, I, S, D>,
            I extends ComponentId<I>,
            D extends ComponentDetail<D>,
            S extends ComponentState<S>>
    ListResult<C> executeTypeSafe(GetAllComponents<T, C, I, S, D> action, InvokeContext context) throws ActionException {
        Dispatcher dispatcher = context.getDispatcher();
        T requestType = action.getComponentType();
        try {
            ListComponentIds<I> listIdsAction = ListComponentIds.build(requestType);
            Set<I> ids = dispatcher.execute(listIdsAction).getCollection();
            List<C> components = Lists.newArrayList();
            for (I componentId : ids) {
                try {
                    GetComponent<T, C, I, S, D> getComponentAction = GetComponent.newInstance(requestType, componentId);
                    C component = dispatcher.execute(getComponentAction).getValue();
                    components.add(component);
                } catch (ActionException cause) {
                    throw new ActionException(action, cause);
                }
            }
            return ListResult.newInstance(components);
        } catch (DispatchException cause) {
            throw new ActionException(action, cause);
        }
    }
}
