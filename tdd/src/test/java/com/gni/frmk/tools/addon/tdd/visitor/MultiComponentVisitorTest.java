package com.gni.frmk.tools.addon.tdd.visitor;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.impl.component.alpha.AlphaComponent;
import com.gni.frmk.tools.addon.tdd.impl.component.beta.BetaComponent;
import com.gni.frmk.tools.addon.tdd.visitor.MultiComponentVisitor.Registration;
import com.google.common.collect.Ordering;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 15:58
 *
 * @author: e03229
 */
public class MultiComponentVisitorTest {

    private class TestMultiComponentVisitor extends MultiComponentVisitor<Class, DelegateComponentVisitor> {

        @Override
        protected Class extractKey(Component<?, ?> visited) {
            return visited.getClass();
        }

        @Override
        protected Class extractKey(DelegateComponentVisitor delegate) {
            return delegate.getComponentType();
        }
    }

    private class DelegateComponentVisitor implements ComponentVisitor {
        private final Class<?> componentType;
        private final List<Component<?, ?>> visitedComponents = Lists.newArrayList();

        private DelegateComponentVisitor(Class<?> componentType) {
            this.componentType = componentType;
        }

        @Override
        public void visitComponent(Component<?, ?> visited) {
            visitedComponents.add(visited);
        }

        public Class<?> getComponentType() {
            return componentType;
        }

        public List<Component<?, ?>> getVisitedComponents() {
            return visitedComponents;
        }
    }

    private DelegateComponentVisitor alphaVisitor;
    private DelegateComponentVisitor betaVisitor;

    private List<Component<?, ?>> alphaComponents;
    private List<Component<?, ?>> betaComponents;
    private List<Component<?, ?>> allComponents;

    @BeforeMethod
    public void setUp() throws Exception {
        alphaVisitor = new DelegateComponentVisitor(AlphaComponent.class);
        betaVisitor = new DelegateComponentVisitor(BetaComponent.class);
        boolean opened = true;
        alphaComponents = newArrayList();
        for (int pos = 0; pos < 3; pos++) {
            alphaComponents.add(AlphaComponent.newInstance(pos, opened));
        }
        betaComponents = newArrayList();
        for (int pos = 0; pos < 3; pos++) {
            betaComponents.add(BetaComponent.newInstance(pos, opened));
        }
        allComponents = newArrayList();
        allComponents.addAll(alphaComponents);
        allComponents.addAll(betaComponents);
    }

    @Test
    public void testVisitComponent() throws Exception {
        TestMultiComponentVisitor visitor = new TestMultiComponentVisitor();
        visitor.registerVisitor(alphaVisitor);
        visitor.registerVisitor(betaVisitor);
        List<Component<?, ?>> unorderedComponents = Ordering.arbitrary().immutableSortedCopy(allComponents);
        for (Component<?, ?> c : unorderedComponents) {
            c.accept(visitor);
        }
        assertThat(alphaVisitor.getVisitedComponents()).containsOnly(alphaComponents.toArray());
        assertThat(betaVisitor.getVisitedComponents()).containsOnly(betaComponents.toArray());
    }

    @Test
    public void testVisitorUnregister() throws Exception {
        TestMultiComponentVisitor visitor = new TestMultiComponentVisitor();
        Registration registration1 = visitor.registerVisitor(alphaVisitor);
        assertThat(registration1).isNotNull();
        assertThat(registration1.isRegistred()).isTrue();
        registration1.unregister();
        assertThat(registration1.isRegistred()).isFalse();
        registration1.unregister();
        assertThat(registration1.isRegistred()).isFalse();
        assertThat(registration1.isValid()).isTrue();
    }

    @Test
    public void testVisitorUnregisterAfterGc() throws Exception {
        TestMultiComponentVisitor visitor = new TestMultiComponentVisitor();
        Registration registration1 = visitor.registerVisitor(alphaVisitor);
        assertThat(registration1.isRegistred()).isTrue();
        registration1.unregister();
        assertThat(registration1.isRegistred()).isFalse();
        assertThat(registration1.isValid()).isTrue();
        alphaVisitor=null;
        visitor=null;
        System.gc();
        System.gc();
        System.gc();
        assertThat(registration1.isValid()).isFalse();
    }
}
