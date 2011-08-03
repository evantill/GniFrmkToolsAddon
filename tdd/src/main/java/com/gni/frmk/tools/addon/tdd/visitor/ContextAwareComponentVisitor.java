package com.gni.frmk.tools.addon.tdd.visitor;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 10:20
 *
 * @author: e03229
 */
public abstract class ContextAwareComponentVisitor implements StatefulComponentVisitor {

    protected abstract class VisitorContext implements StatefulComponentVisitor {
        private final VisitorParsingStrategy parsingStrategy;

        protected VisitorContext(VisitorParsingStrategy parsingStrategy) {
            this.parsingStrategy = parsingStrategy;
        }

        @Override
        public void startVisit() {
            clearContext();
        }

        protected abstract void clearContext();

        @Override
        public void visitComponent(Component<?, ?> visited) {
            addComponentInContext(visited);
        }

        protected abstract void addComponentInContext(Component<?, ?> visited);

        @Override
        public void endVisit() {
            visitComponentInOrder();
        }

        private void visitComponentInOrder() {
            for (Component<?, ?> visited : parsingStrategy.selectComponentsInOrderFrom(getComponentsInContext())) {
                parsingStrategy.visitComponent(visited);
            }
        }

        protected abstract Iterable<Component<?, ?>> getComponentsInContext();

    }

    protected interface VisitorParsingStrategy extends ComponentVisitor {
        Iterable<Component<?, ?>> selectComponentsInOrderFrom(Iterable<Component<?, ?>> components);

    }

    private VisitorContext context;

    @Override
    public void startVisit() {
        context = newContext();
        context.startVisit();
    }

    private VisitorContext newContext() {
        VisitorParsingStrategy strategy = defineParsingStrategy();
        return newContextWithStrategy(strategy);
    }

    protected abstract VisitorParsingStrategy defineParsingStrategy();

    protected abstract VisitorContext newContextWithStrategy(VisitorParsingStrategy strategy);

    @Override
    public void endVisit() {
        context.endVisit();
    }

    @Override
    public void visitComponent(Component<?, ?> visited) {
        context.visitComponent(visited);
    }
}
