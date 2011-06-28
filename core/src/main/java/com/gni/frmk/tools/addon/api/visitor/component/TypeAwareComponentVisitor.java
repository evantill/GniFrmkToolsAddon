package com.gni.frmk.tools.addon.api.visitor.component;

import com.gni.frmk.tools.addon.api.visitor.TypeAwareVisitor;
import com.gni.frmk.tools.addon.model.component.Component;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 12:07
 *
 * @author: e03229
 */
public class TypeAwareComponentVisitor extends TypeAwareVisitor<Class<?>, TypedComponentVisitor, Component<?, ?, ?, ?, ?>>
        implements ComponentVisitor {

    public TypeAwareComponentVisitor(String delegateMathodPrefix, Iterable<TypedComponentVisitor> delegated) {
        super(delegateMathodPrefix, delegated);
    }

    @Override
    protected Class<?> extractKey(Method m) {
        return m.getParameterTypes()[0];
    }

    @Override
    public void visitComponent(Component<?, ?, ?, ?, ?> visited) {
        Class<? extends Component> visitedClass = visited.getClass();
        getDelegate(visitedClass).invoke(visited);
    }
}
