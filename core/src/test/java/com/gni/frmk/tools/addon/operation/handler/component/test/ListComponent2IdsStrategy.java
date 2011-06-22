package com.gni.frmk.tools.addon.operation.handler.component.test;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.test.Component2Id;
import com.gni.frmk.tools.addon.model.component.test.Component2Type;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 17:30
 *
 * @author: e03229
 */
public class ListComponent2IdsStrategy
        implements ListComponentIdsStrategy<Component2Type, Component2Id> {
    @Override
    public Set<Component2Id> listIds(InvokeContext context) throws ServiceException {
        HashSet<Component2Id> ids = Sets.newHashSet();
        ids.add(Component2Id.build(1));
        ids.add(Component2Id.build(2));
        return ids;
    }

    @Override
    public Component2Type getComponentType() {
        return Component2Type.TYPE;
    }
}
