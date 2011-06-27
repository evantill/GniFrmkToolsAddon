package com.gni.frmk.tools.addon.operation.dispatcher;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.model.component.test.Component2Type;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentTypes;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.util.WeldRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 17:45
 *
 * @author: e03229
 */
@RunWith(WeldRunner.class)
public class SimpleDispatcherTest {

    @Inject
    ActionHandlerRegistry<InvokeContext> registry;

    @Test
    public void testCasting() throws DispatchException {
        final ServiceContext ctx = Mockito.mock(ServiceContext.class);
        SimpleDispatcher<InvokeContext> dispatcher = new SimpleDispatcher<InvokeContext>(registry) {
            @Override
            public InvokeContext createContext() {
                return new InvokeContext(this, ctx);
            }
        };
        ListComponentTypes action = new ListComponentTypes();
        Set<ComponentType<?,?,?,?,?>> types = dispatcher.execute(action).getCollection();
        assertThat(types).isNotNull().containsOnly(Component1Type.TYPE, Component2Type.TYPE);
    }
}
