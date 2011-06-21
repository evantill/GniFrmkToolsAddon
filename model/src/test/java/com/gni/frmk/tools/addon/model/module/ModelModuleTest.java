package com.gni.frmk.tools.addon.model.module;

import com.gni.frmk.tools.addon.model.component.test.Component1;
import com.gni.frmk.tools.addon.model.module.ModuleResource.ModelContext;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 20:40
 *
 * @author: e03229
 */
public class ModelModuleTest {

    public static final class Injected {
        private final Set<ModelContext> contexts;

        @Inject
        public Injected(Set<ModelContext> contexts) {this.contexts = contexts;}

        public Set<ModelContext> getContexts() {
            return contexts;
        }
    }

    @Test
    public void testConfigure() throws Exception {
        Injector injector = Guice.createInjector(new ModelModule() {
            @Override
            protected void registerModelContextPackages() {
                registerModelContextPackage(Component1.class.getPackage());
            }
        });
        Injected injected = injector.getInstance(Injected.class);
        assertThat(injected).isNotNull();
        assertThat(injected.getContexts()).isNotNull();
        assertThat(injected.getContexts().iterator().next().getModelPackages()).isNotNull().hasSize(1).containsOnly(Component1.class.getPackage());
    }
}
