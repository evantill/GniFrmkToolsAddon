package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.test.Component1Id;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.model.component.test.Component2Id;
import com.gni.frmk.tools.addon.model.component.test.Component2Type;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 14:25
 *
 * @author: e03229
 */
public class ListComponentIdsHandlerTest {

    public static final class TestStrategy1
            implements ListComponentIdsStrategy<Component1Type, Component1Id> {

        @Override
        public Component1Type getComponentType() {
            return Component1Type.TYPE;
        }

        @Override
        public Set<Component1Id> listIds(InvokeContext context) throws ServiceException {
            Set<Component1Id> result = Sets.newHashSet();
            result.addAll(asList(idsType1));
            return result;
        }

    }

    public static final class TestStrategy2
            implements ListComponentIdsStrategy<Component2Type, Component2Id> {

        @Override
        public Component2Type getComponentType() {
            return Component2Type.TYPE;
        }

        @Override
        public Set<Component2Id> listIds(InvokeContext context) throws ServiceException {
            Set<Component2Id> result = Sets.newHashSet();
            result.addAll(asList(idsType2));
            return result;
        }

    }

    private static Component1Id[] idsType1 = new Component1Id[]{
            Component1Id.build("component 1"),
            Component1Id.build("component 2")
    };


    private static Component2Id[] idsType2 = new Component2Id[]{
            Component2Id.build(1),
            Component2Id.build(2),
            Component2Id.build(3),
            Component2Id.build(4)
    };

    @Test
    public void testExecuteStrategy1() throws Exception {
        ActionContext<ListComponentIdsStrategy<?, ?>> context = ActionContext.newContext();
        context.registerStrategy(new TestStrategy1());
        context.registerStrategy(new TestStrategy2());
        ListComponentIdsHandler handler = new ListComponentIdsHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        ListComponentIds<Component1Id> action1 = ListComponentIds.build(Component1Type.TYPE);
        SetResult<Component1Id> result1 = handler.executeTypeSafe(action1, ctx);
        assertThat(result1).isNotNull();
        assertThat(result1.getCollection()).isNotNull().hasSize(2).containsOnly((Object[]) idsType1);
    }

    @Test
    public void testExecuteStrategy2() throws Exception {
        ActionContext<ListComponentIdsStrategy<?, ?>> context = ActionContext.newContext();
        context.registerStrategy(new TestStrategy1());
        context.registerStrategy(new TestStrategy2());
        ListComponentIdsHandler handler = new ListComponentIdsHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        ListComponentIds<Component2Id> action2 = ListComponentIds.build(Component2Type.TYPE);
        SetResult<Component2Id> result2 = handler.executeTypeSafe(action2, ctx);
        assertThat(result2).isNotNull();
        assertThat(result2.getCollection()).isNotNull().hasSize(4).containsOnly((Object[]) idsType2);
    }

    @Test(expected = ActionException.class)
    public void testExecuteStrategyUnknown() throws Exception {
        ActionContext<ListComponentIdsStrategy<?, ?>> context = ActionContext.newContext();
        context.registerStrategy(new TestStrategy2());
        ListComponentIdsHandler handler = new ListComponentIdsHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        ListComponentIds<Component1Id> action1 = ListComponentIds.build(Component1Type.TYPE);
        SetResult<Component1Id> result1 = handler.executeTypeSafe(action1, ctx);
        fail("exception must be thrown");
    }
}

