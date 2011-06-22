package com.gni.frmk.tools.addon.model;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;
import com.gni.frmk.tools.addon.model.component.test.Component1;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.util.WeldRunner;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 22/06/11
 * Time: 14:12
 *
 * @author: e03229
 */
@RunWith(WeldRunner.class)
public class ModelResourceTest {

    @Inject
    Injected injected;

    @Inject
    Injected injectedCopy;

    @Test
    public void testConfigure() throws Exception {
        assertThat(injected).isNotNull();
        assertThat(injectedCopy).isNotNull().isEqualTo(injected);
        assertThat(injected.getContexts()).isNotNull();
        assertThat(injected.getContexts()).isNotNull()
                .hasSize(4)
                .containsOnly(
                        Configuration.class.getPackage(),
                        Component.class.getPackage(),
                        BaseComponent.class.getPackage(),
                        Component1.class.getPackage()
                );
    }

    @Singleton
    public static final class Injected {

        private final Set<Package> contexts = Sets.newHashSet();

        @Inject
        public Injected(@Any Instance<ModelResource> ctx) {
            for (ModelResource res : ctx) {
                contexts.addAll(res.getModelPackages());
            }
        }

        public Set<Package> getContexts() {
            return contexts;
        }
    }
}
