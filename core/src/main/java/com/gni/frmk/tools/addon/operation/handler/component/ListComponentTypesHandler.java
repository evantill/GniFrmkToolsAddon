package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentTypes;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.google.common.collect.Sets;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 12:39
 *
 * @author: e03229
 */
public class ListComponentTypesHandler
        implements ActionHandler<ListComponentTypes, SetResult<? extends ComponentType<?, ?, ?, ?, ?>>, ExecutionContext> {

    private static final TypeLiteral<ListComponentTypes> TYPE_LITERAL = new TypeLiteral<ListComponentTypes>() {
    };
    private final Set<? extends ComponentType<?, ?, ?, ?, ?>> types;

    @Inject
    public ListComponentTypesHandler(Instance<ComponentType<?, ?, ?, ?, ?>> types) {
        this.types = Sets.newHashSet(types);
    }

    @Override
    public TypeLiteral<ListComponentTypes> getActionType() {
        return TYPE_LITERAL;
    }

    @Override
    public SetResult<? extends ComponentType<?, ?, ?, ?, ?>> execute(ListComponentTypes action, ExecutionContext context)
            throws ActionException {
        return SetResult.newInstance(types);
    }
}
