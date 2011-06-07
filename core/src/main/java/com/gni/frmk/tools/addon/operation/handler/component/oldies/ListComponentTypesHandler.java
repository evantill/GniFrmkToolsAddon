package com.gni.frmk.tools.addon.operation.handler.component.oldies;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentTypes;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:57
 *
 * @author: e03229
 */
public class ListComponentTypesHandler
        implements ActionHandler<ListComponentTypes, SetResult<ComponentType>, ExecutionContext> {

    @Override
    public Class<? extends ListComponentTypes> getActionType() {
        return ListComponentTypes.class;
    }

    @Override
    public SetResult<ComponentType> execute(ListComponentTypes action, ExecutionContext context) throws ActionException {
        //TODO utiliser un registre
        throw new IllegalStateException("not yet implemented");
    }
}
