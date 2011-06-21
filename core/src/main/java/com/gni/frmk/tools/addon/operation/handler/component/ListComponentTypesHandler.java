package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentTypes;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.google.inject.Inject;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 12:39
 *
 * @author: e03229
 */
public class ListComponentTypesHandler
        implements ActionHandler<ListComponentTypes, SetResult<ComponentType>, ExecutionContext> {

    private final Set<ComponentType> types;

    @Inject
    public ListComponentTypesHandler(Set<ComponentType> types) {
        this.types = types;
    }

    @Override
    public Class<?> getActionType() {
        return ListComponentTypes.class;
    }

    @Override
    public SetResult<ComponentType> execute(ListComponentTypes action, ExecutionContext context)
            throws ActionException {
        return SetResult.newInstance(types);
    }
}
