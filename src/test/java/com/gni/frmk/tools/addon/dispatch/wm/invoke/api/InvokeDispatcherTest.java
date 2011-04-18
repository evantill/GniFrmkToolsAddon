package com.gni.frmk.tools.addon.dispatch.wm.invoke.api;

import com.gni.frmk.tools.addon.action.wm.art.connection.DisableConnection;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.ActionNotFoundException;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.api.action.Result;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.wm.data.*;
import com.wm.lang.ns.NSName;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;

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

    @Test(expected = ActionNotFoundException.class)
    public void executeServiceNotFound() throws DispatchException {
        InvokeServiceRegistry registry = new InvokeServiceRegistry();
        TestResult result = new TestResult();
        IData inputMock = Mockito.mock(IData.class);
        TestAction action = new TestAction(result, inputMock);
        InvokeContext contextMock = Mockito.mock(InvokeContext.class);
        InvokeDispatcher dispatcher = new InvokeDispatcher(registry, contextMock);
        dispatcher.execute(action);
        Mockito.verifyZeroInteractions(contextMock);
        Assert.fail("action not found exception should be raised");
    }

    @Test
    public void testExecute() throws Exception {
        InvokeServiceRegistry registry = new InvokeServiceRegistry();
        IData inputMock = Mockito.mock(IData.class);
        IData outputMock = Mockito.mock(IData.class);
        TestResult result = new TestResult();
        TestAction action = new TestAction(result, inputMock);
        InvokeContext contextMock = Mockito.mock(InvokeContext.class);
        String serviceName = "theServiceNameToTest";
        NSName service = NSName.create(serviceName);
        Mockito.when(contextMock.invoke(action, service, inputMock)).thenReturn(outputMock);
        InvokeDispatcher dispatcher = new InvokeDispatcher(registry, contextMock);
        //init registry
        TestActionHandler handler = new TestActionHandler(serviceName);
        registry.addHandler(handler);
        //use dispatcher
        TestResult dispatcherResult = dispatcher.execute(action);
        Mockito.verify(contextMock).invoke(same(action), eq(service), same(inputMock));
        Assert.assertSame(result, dispatcherResult);
    }

    @Test
    public void testRegisterHandler() throws Exception {
        InvokeServiceRegistry registry = Mockito.spy(new InvokeServiceRegistry());
        InvokeContext ctx = Mockito.mock(InvokeContext.class);
        InvokeDispatcher dispatcher = new InvokeDispatcher(registry, ctx);

        //define action
        Action<Result> action = (Action<Result>) Mockito.mock(Action.class);
        ActionHandler<Action<Result>, Result, InvokeContext> handler = Mockito.mock(ActionHandler.class);
        Mockito.when(handler.getActionType()).thenReturn((Class<Action<Result>>) action.getClass());

        dispatcher.registerHandler(handler);
        dispatcher.execute(action);
        Mockito.verify(registry).addHandler(handler);
        Mockito.verify(registry).findInvoker(action);
        Mockito.verifyNoMoreInteractions(registry);
    }

    @Test
    public void testDisableConnection() throws Exception {
        InvokeServiceRegistry registry = new InvokeServiceRegistryBuilder().defineServices().build();
        InvokeContext contextMock = Mockito.mock(InvokeContext.class);
        String aliasName = "aliasForTestConnection";
        DisableConnection action = new DisableConnection(aliasName);
        InvokeDispatcher dispatcher = new InvokeDispatcher(registry, contextMock);
        NoResult result = dispatcher.execute(action);
        Mockito.verify(contextMock)
               .invoke(same(action), eq(NSName.create("pub.art.connection:disableConnection")), Matchers.<IData>any());
    }


}
