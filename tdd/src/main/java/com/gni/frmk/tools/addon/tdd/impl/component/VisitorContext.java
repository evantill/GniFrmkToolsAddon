package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/08/11
 * Time: 20:40
 *
 * @author: e03229
 */
public class VisitorContext implements ComponentVisitor {

    public static interface VisitorStrategy {
        Iterable<? extends Component<?, ?>> appplyVisitorStrategy(Iterable<? extends Component<?, ?>> components);
    }

    private final ComponentVisitor delegate;
    private final VisitorStrategy strategy;
    private final List<Component<?, ?>> components = Lists.newArrayList();

    public VisitorContext(ComponentVisitor delegate, VisitorStrategy strategy) {
        this.delegate = delegate;
        this.strategy = strategy;
    }

    public void visitComponent(Component<?, ?> visited) {
        clearContext();
        addComponentsToContext(visited);
        visitComponentsUsingStrategy();
    }

    private void clearContext() {
        components.clear();
    }

    private void addComponentsToContext(Component<?, ?> visited) {
        visited.accept(new ComponentVisitor() {
            @Override
            public void visitComponent(Component<?, ?> component) {
                addComponent(component);
            }
        });
    }

    private void addComponent(Component<?, ?> component) {
        components.add(component);
    }

    private void visitComponentsUsingStrategy() {
        for (Component<?, ?> visited : applyVisitorStrategy()) {
            delegate.visitComponent(visited);
        }
    }

    private Iterable<? extends Component<?, ?>> applyVisitorStrategy() {
        return strategy.appplyVisitorStrategy(components);
    }
}
