package com.gni.frmk.tools.addon.operation.module;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.test.ListComponent1IdsStrategy;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 20:55
 *
 * @author: e03229
 */
public class ActionHandlerModuleTest {

    private static final TypeLiteral<ListComponentIdsStrategy<?, ?>> TYPE = new TypeLiteral<ListComponentIdsStrategy<?, ?>>() {
    };

    public static final class Injected {
        private final Set<ActionHandler<?, ?, ?>> handlers;


        @Inject
        public Injected(Set<ActionHandler<?, ?, ?>> handlers) {this.handlers = handlers;}

        public ListComponentIdsHandler getHandler() {
            return (ListComponentIdsHandler) handlers.iterator().next();
        }
    }

    @Test
    public void testConfigure() throws Exception {
        Injector injector = Guice.createInjector(
                new ActionContextModule<ListComponentIdsStrategy<?,?>>(TYPE) {

                    @Override
                    protected void registerStrategies() {
                        registerStrategy(ListComponent1IdsStrategy.class);
                    }
                },
                new ActionHandlerModule() {
                    @Override
                    protected void registerActionHandlers() {
                        registerActionHandler(ListComponentIdsHandler.class);
                    }
                }
        );

        Injected injected = injector.getInstance(Injected.class);
        assertThat(injected.getHandler()).isNotNull();
        ListComponentIdsHandler handler = injected.getHandler();
        InvokeContext mock = Mockito.mock(InvokeContext.class);
        SetResult<? extends ComponentId<?>> result = handler.execute(ListComponentIds.build(Component1Type.TYPE), mock);
        assertThat(result).isNotNull();
        assertThat(result.getCollection()).isNotNull().hasSize(3);
    }
}

