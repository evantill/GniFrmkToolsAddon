package com.gni.frmk.tools.addon.operation.dispatcher;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.component.test.Component1Id;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentTypes;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentTypesHandler;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.gni.frmk.tools.addon.util.WeldRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 22/06/11
 * Time: 15:15
 *
 * @author: e03229
 */
@RunWith(WeldRunner.class)
public class ActionHandlerRegistryTest {

    @Inject
    ActionHandlerRegistry<InvokeContext> registry;

    @Test
    public void testGetSimpleHandler() throws Exception {
        ListComponentTypes action = ListComponentTypes.newInstance();
        ActionHandler<ListComponentTypes, SetResult<? extends ComponentType<?, ?, ?, ?, ?>>, InvokeContext> handler = registry.getHandler(action);
        assertThat(handler).isNotNull().isInstanceOf(ListComponentTypesHandler.class);
    }

    @Test
    public void testGetStrategyHandler() throws Exception {
        ListComponentIds<Component1Id> action1 = ListComponentIds.build(Component1Type.TYPE);
        ActionHandler<ListComponentIds<Component1Id>, SetResult<Component1Id>, InvokeContext> handler = registry.getHandler(action1);
        assertThat(handler).isNotNull().isInstanceOf(ListComponentIdsHandler.class);
        InvokeContext mock = Mockito.mock(InvokeContext.class);
        Set<Component1Id> result1 = handler.execute(action1, mock).getCollection();
        assertThat(result1).isNotNull().hasSize(3);
    }

}
