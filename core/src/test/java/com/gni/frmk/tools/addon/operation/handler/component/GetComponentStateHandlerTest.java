package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.api.ServiceNotFoundException;
import com.gni.frmk.tools.addon.model.component.test.*;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import org.junit.Test;

import java.util.NoSuchElementException;

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
public class GetComponentStateHandlerTest {

    public static final class TestStrategy1
            implements GetComponentStateStrategy<Component1Type, Component1Id, Component1State> {

        @Override
        public Component1Type getComponentType() {
            return Component1Type.TYPE;
        }

        @Override
        public Component1State getStateOrUnknown(Component1Id componentId, InvokeContext context) throws ServiceException {
            try {
                return getState(componentId, context);
            } catch (NoSuchElementException unknown) {
                return Component1State.UNKNOWN;
            } catch (ServiceNotFoundException unknown) {
                return Component1State.UNKNOWN;
            }
        }

        @Override
        public Component1State getState(Component1Id componentId, InvokeContext context) throws ServiceException {
            return stateType1;
        }

    }

    public static final class TestStrategy2
            implements GetComponentStateStrategy<Component2Type, Component2Id, Component2State> {

        @Override
        public Component2Type getComponentType() {
            return Component2Type.TYPE;
        }

        @Override
        public Component2State getStateOrUnknown(Component2Id componentId, InvokeContext context) throws ServiceException {
            try {
                return getState(componentId, context);
            } catch (NoSuchElementException unknown) {
                return Component2State.UNKNOWN;
            } catch (ServiceNotFoundException unknown) {
                return Component2State.UNKNOWN;
            }
        }

        @Override
        public Component2State getState(Component2Id componentId, InvokeContext context) throws ServiceException {
            return stateType2;
        }
    }

    private static Component1Id idType1 = Component1Id.build("component 1");
    private static Component1State stateType1 = Component1State.builder().enable(true).validate().build();
    private static Component2Id idType2 = Component2Id.build(1);
    private static Component2State stateType2 = Component2State.builder().enable(true).active(true).validate().build();

    @Test
    public void testExecuteStrategy1() throws Exception {
        ActionContext<GetComponentStateStrategy<?, ?, ?>> context = ActionContext.newContext();
        context.registerStrategy(new TestStrategy1());
        context.registerStrategy(new TestStrategy2());
        GetComponentStateHandler handler = new GetComponentStateHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        GetComponentState<Component1Id, Component1State> action1 = GetComponentState.newInstance(Component1Type.TYPE, idType1);
        SingleResult<Component1State> result1 = handler.executeTypeSafe(action1, ctx);
        assertThat(result1).isNotNull();
        assertThat(result1.getValue()).isNotNull().isEqualTo(stateType1);
    }

    @Test
    public void testExecuteStrategy2() throws Exception {
        ActionContext<GetComponentStateStrategy<?, ?, ?>> context = ActionContext.newContext();
        context.registerStrategy(new TestStrategy1());
        context.registerStrategy(new TestStrategy2());
        GetComponentStateHandler handler = new GetComponentStateHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        GetComponentState<Component2Id, Component2State> action2 = GetComponentState.newInstance(Component2Type.TYPE, idType2);
        SingleResult<Component2State> result2 = handler.executeTypeSafe(action2, ctx);
        assertThat(result2).isNotNull();
        assertThat(result2.getValue()).isNotNull().isEqualTo(stateType2);
    }

    @Test(expected = ActionException.class)
    public void testExecuteStrategyUnknown() throws Exception {
        ActionContext<GetComponentStateStrategy<?, ?, ?>> context = ActionContext.newContext();
        context.registerStrategy(new TestStrategy2());
        GetComponentStateHandler handler = new GetComponentStateHandler(context);
        InvokeContext ctx = mock(InvokeContext.class);
        GetComponentState<Component1Id, Component1State> action1 = GetComponentState.newInstance(Component1Type.TYPE, idType1);
        SingleResult<Component1State> result1 = handler.executeTypeSafe(action1, ctx);
        fail("exception must be thrown");
    }
}

