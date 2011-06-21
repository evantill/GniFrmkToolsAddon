package com.gni.frmk.tools.addon.operation.handler.component.test;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.test.Component1Id;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
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
public class ListComponent1IdsStrategy
        implements ListComponentIdsStrategy<Component1Type, Component1Id> {
    @Override
    public Set<Component1Id> listIds(InvokeContext context) throws ServiceException {
        HashSet<Component1Id> ids = Sets.newHashSet();
        ids.add(Component1Id.build("id 1"));
        ids.add(Component1Id.build("id 2"));
        ids.add(Component1Id.build("id 3"));
        return ids;
    }

    @Override
    public Component1Type getComponentType() {
        return Component1Type.TYPE;
    }
}
