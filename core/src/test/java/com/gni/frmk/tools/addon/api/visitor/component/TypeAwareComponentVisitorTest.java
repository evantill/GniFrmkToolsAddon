package com.gni.frmk.tools.addon.api.visitor.component;

import com.gni.frmk.tools.addon.api.visitor.component.test.TestComponentVisitor;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.test.*;
import com.gni.frmk.tools.addon.util.WeldRunner;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 13:47
 *
 * @author: e03229
 */
@RunWith(WeldRunner.class)
public class TypeAwareComponentVisitorTest {

    @Alternative
    private static final class Visitor extends TypeAwareComponentVisitor {
        private Visitor(Iterable<TypedComponentVisitor> delegated) {
            super("visitComponent", delegated);
        }
    }

    @Inject
    Visitor injectedVisitor;
    @Inject
    TestVisitor injectedComponentVisitor;

    public static class TestVisitorProducer {
        @Produces
        Visitor newVisitor(TestVisitor delegate) {
            return new Visitor(Collections.<TypedComponentVisitor>singleton(delegate));
        }
    }

    @Singleton
    public static final class TestVisitor implements TestComponentVisitor {

        private final SortedSet<ComponentId<?>> ids = Sets.newTreeSet(new Comparator<ComponentId<?>>() {
            @Override
            public int compare(ComponentId<?> o1, ComponentId<?> o2) {
                return ComparisonChain.start()
                                      .compare(o1.getClass().getName(), o2.getClass().getName())
                                      .compare(o1.hashCode(), o2.hashCode())
                                      .result();
            }
        });

        @Override
        public void visitComponentComponent1(Component1 visited) {
            ids.add(visited.getId());
        }

        @Override
        public void visitComponentComponent2(Component2 visited) {
            ids.add(visited.getId());
        }

        public SortedSet<ComponentId<?>> getIds() {
            return ids;
        }
    }

    @Test
    public void testVisitComponent() throws Exception {
        final Component1Id c1Id = Component1Id.build("component 1");
        final Component1 c1 = Component1.builder()
                                        .id(c1Id)
                                        .detail(SimpleDetail.newInstance("description"))
                                        .state(Component1State.newInstance(true))
                                        .validate()
                                        .build();
        final Component2Id c2Id = Component2Id.build(1);
        final Component2 c2 = Component2.builder()
                                        .id(c2Id)
                                        .detail(SimpleDetail.newInstance("description"))
                                        .state(Component2State.newInstance(true, true))
                                        .validate()
                                        .build();
        injectedVisitor.visitComponent(c1);
        injectedVisitor.visitComponent(c2);

        SortedSet<ComponentId<?>> visitedIds = injectedComponentVisitor.getIds();
        assertThat(visitedIds).isNotNull().containsOnly(c1Id, c2Id);

    }
}
