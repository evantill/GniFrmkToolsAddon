package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.service.test.Module1TestService;
import com.gni.frmk.tools.addon.invoker.service.test.Module1TestService.Input;
import com.gni.frmk.tools.addon.invoker.service.test.Module1TestService.Output;
import com.gni.frmk.tools.addon.model.component.test.Component1;
import com.gni.frmk.tools.addon.model.component.test.Component1Id;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.model.module.ModuleResource.ModelContext;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 19:00
 *
 * @author: e03229
 */
public class ModuleResoureTest {

    TestModuleResource module;
    Injector injector;

    public static final class Injected {
        private final Module1TestService invokeService;
        private final Set<ModelContext> modelContextSet;
        private final ListComponentIdsHandler listComponentIdsHandler;
        private final GetComponentDetailHandler getComponentDetailHandler;
        private final GetComponentStateHandler getComponentStateHandler;
        private final ChangeComponentStateHandler changeComponentStateHandler;

        @Inject
        public Injected(Module1TestService invokeService, Set<ModelContext> modelContextSet, ListComponentIdsHandler listComponentIdsHandler, GetComponentDetailHandler getComponentDetailHandler, GetComponentStateHandler getComponentStateHandler, ChangeComponentStateHandler changeComponentStateHandler) {
            this.invokeService = invokeService;
            this.modelContextSet = modelContextSet;
            this.listComponentIdsHandler = listComponentIdsHandler;
            this.getComponentDetailHandler = getComponentDetailHandler;
            this.getComponentStateHandler = getComponentStateHandler;
            this.changeComponentStateHandler = changeComponentStateHandler;
        }

        public Module1TestService getInvokeService() {
            return invokeService;
        }

        public Set<ModelContext> getModelContextSet() {
            return modelContextSet;
        }

        public ListComponentIdsHandler getListComponentIdsHandler() {
            return listComponentIdsHandler;
        }

        public GetComponentDetailHandler getGetComponentDetailHandler() {
            return getComponentDetailHandler;
        }

        public GetComponentStateHandler getGetComponentStateHandler() {
            return getComponentStateHandler;
        }

        public ChangeComponentStateHandler getChangeComponentStateHandler() {
            return changeComponentStateHandler;
        }
    }

    @Before
    public void injectorSetup() {
        module = new TestModuleResource();
        injector = Guice.createInjector(module);
    }

    @Test
    public void testInvokeServiceRegistration() throws ServiceException {
        Injected injected = injector.getInstance(Injected.class);
        ServiceContext mock = mock(ServiceContext.class);
        assertThat(injected.getInvokeService()).isNotNull();
        Output output = injected.getInvokeService().invoke(new Input("x1", "x2"), mock);
        assertThat(output).isNotNull();
        assertThat(output.getConcatMessage()).isNotNull().isEqualTo("x1+x2");
    }

    @Test
    public void testModelContextRegistration() throws ServiceException {
        Injected injected = injector.getInstance(Injected.class);
        Package[] modelPackages = injected.getModelContextSet().iterator().next().getModelPackages();
        assertThat(modelPackages).isNotNull()
                .containsOnly(Component1.class.getPackage());
    }

    @Test
    public void testListComponentIdsHandlerRegistration() throws ServiceException, ActionException {
        Injected injected = injector.getInstance(Injected.class);
        InvokeContext mock = mock(InvokeContext.class);
        ListComponentIdsHandler handler = injected.getListComponentIdsHandler();
        SetResult<Component1Id> result = handler.executeTypeSafe(ListComponentIds.build(Component1Type.TYPE), mock);
        assertThat(result).isNotNull();
        assertThat(result.getCollection()).isNotNull().hasSize(3);
    }
}
