package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.dispatcher.Action;
import com.gni.frmk.tools.addon.dispatcher.ActionException;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.dispatcher.ActionNotFoundException;
import com.gni.frmk.tools.addon.dispatcher.Result;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.wm.data.*;
import com.wm.lang.ns.NSName;
import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/03/11
 * Time: 15:28
 *
 * @author: e03229
 */
public class InvokeDispatcherTest {
    public static class TestAction implements Action<TestResult> {
        private final TestResult result;
        private final IData input;

        public TestAction(TestResult result, IData input) {
            this.result = result;
            this.input = input;
        }

        public TestResult getResult() {
            return result;
        }

        public IData getInput() {
            return input;
        }
    }

    public static class TestResult implements Result {

    }

    public static class TestActionHandler extends AbstractInvokeHandler<TestAction, TestResult> {

        public TestActionHandler(String serviceName) {
            super(serviceName);
        }

        @Override
        protected TestResult parseOutput(TestAction action, IData output) {
            return action.getResult();
        }

        @Override
        protected IData prepareInput(TestAction in) {
            return in.getInput();
        }

        @Override
        public Class<TestAction> getActionType() {
            return TestAction.class;
        }
    }


    public static class TestInvokeDispatcher extends InvokeDispatcher {
        public InvokeServiceRegistry registry;
        public InvokeContext context;

        public TestInvokeDispatcher(InvokeServiceRegistry registry, InvokeContext context) {
            this.registry = registry;
            this.context = context;
        }

        @Override
        protected InvokeServiceRegistry getHandlerRegistry() {
            return registry;
        }

        @Override
        protected InvokeContext getInvokeContext() {
            return context;
        }
    }

    @Test(expected = ActionNotFoundException.class)
    public void executeServiceNotFound() throws ActionException {
        InvokeServiceRegistry registry = new InvokeServiceRegistry();
        TestResult result = new TestResult();
        IData inputMock = mock(IData.class);
        TestAction action = new TestAction(result, inputMock);
        InvokeContext contextMock = mock(InvokeContext.class);
        TestInvokeDispatcher dispatcher = new TestInvokeDispatcher(registry, contextMock);
        dispatcher.execute(action);
        verifyZeroInteractions(contextMock);
        fail("action not found exception should be raised");
    }

    @Test
    public void testExecute() throws Exception {
        InvokeServiceRegistry registry = new InvokeServiceRegistry();
        IData inputMock = mock(IData.class);
        IData outputMock = mock(IData.class);
        TestResult result = new TestResult();
        TestAction action = new TestAction(result, inputMock);
        InvokeContext contextMock = mock(InvokeContext.class);
        String serviceName = "theServiceNameToTest";
        NSName service = NSName.create(serviceName);
        when(contextMock.invoke(action, service, inputMock)).thenReturn(outputMock);
        TestInvokeDispatcher dispatcher = new TestInvokeDispatcher(registry, contextMock);
        //init registry
        TestActionHandler handler = new TestActionHandler(serviceName);
        registry.addHandler(handler);
        //use dispatcher
        TestResult dispatcherResult = dispatcher.execute(action);
        verify(contextMock).invoke(same(action), eq(service), same(inputMock));
        assertSame(result, dispatcherResult);
    }

    @Test
    public void testRegisterHandler() throws Exception {
        InvokeServiceRegistry registry = spy(new InvokeServiceRegistry());
        InvokeContext ctx = mock(InvokeContext.class);
        TestInvokeDispatcher dispatcher = new TestInvokeDispatcher(registry, ctx);

        //define action
        Action<Result> action = (Action<Result>) mock(Action.class);
        ActionHandler<Action<Result>, Result, InvokeContext> handler = mock(ActionHandler.class);
        when(handler.getActionType()).thenReturn((Class<Action<Result>>) action.getClass());

        dispatcher.registerHandler(handler);
        dispatcher.execute(action);
        verify(registry).addHandler(handler);
        verify(registry).findInvoker(action);
        verifyNoMoreInteractions(registry);
    }
}
