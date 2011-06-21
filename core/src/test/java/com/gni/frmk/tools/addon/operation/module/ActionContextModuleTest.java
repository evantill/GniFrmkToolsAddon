package com.gni.frmk.tools.addon.operation.module;

import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ActionContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.test.ListComponent1IdsStrategy;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 20:46
 *
 * @author: e03229
 */
public class ActionContextModuleTest {
    private static final TypeLiteral<ListComponentIdsStrategy<?,?>> TYPE = new TypeLiteral<ListComponentIdsStrategy<?,?>>() {
    };

    public static final class Injected {
        private final ActionContext<ListComponentIdsStrategy<?, ?>> context;

        @Inject
        public Injected(ActionContext<ListComponentIdsStrategy<?, ?>> context) {this.context = context;}

        public ActionContext<ListComponentIdsStrategy<?, ?>> getContext() {
            return context;
        }
    }

    @Test
    public void testConfigure() throws Exception {
        Injector injector = Guice.createInjector(new ActionContextModule<ListComponentIdsStrategy<?,?>>(TYPE) {
            @Override
            protected void registerStrategies() {
                registerStrategy(ListComponent1IdsStrategy.class);
            }
        });
        Injected injected = injector.getInstance(Injected.class);
        assertThat(injected.getContext()).isNotNull();
        ListComponentIdsStrategy<?, ?> strategy = injected.getContext().selectStrategy(Component1Type.TYPE);
        assertThat(strategy).isNotNull();
        InvokeContext mock = Mockito.mock(InvokeContext.class);
        assertThat(strategy.listIds(mock)).isNotNull().hasSize(3);
    }
}
