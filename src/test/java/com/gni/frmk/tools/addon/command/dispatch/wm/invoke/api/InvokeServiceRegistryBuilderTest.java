package com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api;

import com.gni.frmk.tools.addon.command.action.wm.art.DisableConnection;
import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.handler.wm.InvokeHandler;
import com.gni.frmk.tools.addon.command.handler.wm.art.DisableConnectionHandler;
import com.gni.frmk.tools.addon.command.result.NoResult;
import com.wm.lang.ns.NSName;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/03/11
 * Time: 18:32
 *
 * @author: e03229
 */
public class InvokeServiceRegistryBuilderTest {

    static final class TestAction implements Action<NoResult> {

    }

    static final class TestInvokerHandler implements InvokeHandler<TestAction, NoResult> {

        @Override
        public Class<TestAction> getActionType() {
            return TestAction.class;
        }

        @Override
        public NoResult execute(TestAction action, InvokeContext context) throws ServiceInvokeException {
            return NoResult.newInstance();
        }

        @Override
        public NSName getService() {
            return NSName.create("bidon");
        }
    }

    @Test
    public void testBuild() throws Exception {
        InvokeServiceRegistryBuilder builder = new InvokeServiceRegistryBuilder();
        TestAction action = new TestAction();
        TestInvokerHandler handler = new TestInvokerHandler();
        InvokeServiceRegistry registry = builder.addHandler(handler).build();
        ActionHandler<TestAction, NoResult, InvokeContext> foundHandler = registry.findInvoker(action);
        Assert.assertEquals(handler, foundHandler);
    }

    @Test
    public void testLoad() throws Exception {
        InvokeServiceRegistryBuilder builder = new InvokeServiceRegistryBuilder().defineServices();
        assertEquals("error loading all services in services file",34,builder.handlers.size());
        InvokeServiceRegistry registry = builder.build();
        DisableConnectionHandler h = (DisableConnectionHandler) registry.findInvoker(new DisableConnection("bidon"));
        Assert.assertNotNull(h);
    }
}
