package com.gni.frmk.tools.addon.tdd.visitor;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 10:12
 *
 * @author: e03229
 */
public class OpenComponentVisitor extends ContextAwareComponentVisitor {

    @Override
    protected final VisitorParsingStrategy defineParsingStrategy() {
        return OpenStrategy.INSTANCE;
    }

    private enum OpenStrategy implements VisitorParsingStrategy, Comparator<Component<?, ?>> {
        INSTANCE;

        @Override
        public int compare(Component<?, ?> o1, Component<?, ?> o2) {
            return ComparisonChain.start()
                                  .compare(o1.getOpenOrder(), o2.getOpenOrder())
                                  .result();
        }

        @Override
        public Iterable<Component<?, ?>> selectComponentsInOrderFrom(Iterable<Component<?, ?>> components) {
            return Ordering.from(this).immutableSortedCopy(components);
        }

        @Override
        public void visitComponent(Component<?, ?> visited) {
            visited.open();
        }
    }

    @Override
    protected VisitorContext newContextWithStrategy(VisitorParsingStrategy strategy) {
        return new OpenContext(strategy);
    }

    private class OpenContext extends VisitorContext {

        private final List<Component<?, ?>> components = Lists.newArrayList();

        private OpenContext(VisitorParsingStrategy parsingStrategy) {
            super(parsingStrategy);
        }

        @Override
        protected void clearContext() {
            components.clear();
        }

        @Override
        protected void addComponentInContext(Component<?, ?> visited) {
            components.add(visited);
        }

        @Override
        protected Iterable<Component<?, ?>> getComponentsInContext() {
            return components;
        }
    }


}
