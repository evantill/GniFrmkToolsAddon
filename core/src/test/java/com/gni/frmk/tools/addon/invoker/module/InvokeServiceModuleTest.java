package com.gni.frmk.tools.addon.invoker.module;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.service.test.Module1TestService;
import com.gni.frmk.tools.addon.invoker.service.test.Module1TestService.Input;
import com.gni.frmk.tools.addon.invoker.service.test.Module1TestService.Output;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 20:36
 *
 * @author: e03229
 */
public class InvokeServiceModuleTest {

    @Test
    public void testConfigure() throws Exception {
        Injector injector = Guice.createInjector(new InvokeServiceModule() {
            @Override
            protected void registerServices() {
                registerService(Module1TestService.class);
            }
        });
        Module1TestService service = injector.getInstance(Module1TestService.class);
        assertThat(service).isNotNull();
        ServiceContext mock = mock(ServiceContext.class);
        Output result = service.invoke(new Input("x1", "x2"), mock);
        assertThat(result.getConcatMessage()).isNotNull().isEqualTo("x1+x2");
    }
}
