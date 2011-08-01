package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.gni.frmk.tools.addon.tdd.impl.component.VisitorContext.VisitorStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/08/11
 * Time: 16:31
 *
 * @author: e03229
 */
public abstract class StatefulComponentVisitor implements ComponentVisitor {

    private final VisitorStrategy strategy;
    private final ComponentVisitor adapter;

    protected StatefulComponentVisitor(VisitorStrategy strategy) {
        this.strategy = strategy;
        this.adapter = new ComponentVisitor() {
            @Override
            public void visitComponent(Component<?, ?> component) {
                visitComponentWithStrategy(component);
            }
        };
    }

    @Override
    public void visitComponent(Component<?, ?> visited) {
        newVisitorContext().visitComponent(visited);
    }

    public abstract void visitComponentWithStrategy(Component<?, ?> visited);

    public VisitorContext newVisitorContext() {
        return new VisitorContext(adapter, strategy);
    }

}
