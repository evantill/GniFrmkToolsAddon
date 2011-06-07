package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.visitor.MethodBuilder.MethodInvocationBuilder;
import com.gni.frmk.tools.addon.visitor.api.ComponentVisitor;
import com.gni.frmk.tools.addon.visitor.api.art.ArtComponentVisitor;
import com.gni.frmk.tools.addon.visitor.api.jms.JmsComponentVisitor;
import com.gni.frmk.tools.addon.visitor.api.root.RootComponentVisitor;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/05/11
 * Time: 08:56
 *
 * @author: e03229
 */
public abstract class TypeAwareComponentVisitor
        implements ComponentVisitor, ArtComponentVisitor, RootComponentVisitor, JmsComponentVisitor {

    private static final Map<Class<? extends Component>, MethodInvocationBuilder<Void>> invokers = Maps.newHashMap();

    @Override
    public void visitComponent(Component<?,?,?, ?, ?> visited) {
        Class<? extends Component> visitedClass = visited.getClass();
        if (!invokers.containsKey(visitedClass)) {
            String simpleName = visitedClass.getSimpleName();
            MethodInvocationBuilder<Void> invoker = MethodInvocationBuilder.builder()
                                                                           .where(getClass())
                                                                           .methodName("visitComponent", simpleName)
                                                                           .compile(Void.TYPE);
            invokers.put(visitedClass, invoker);
        }
        invokers.get(visitedClass).invokeNoCheckedExceptionException(this, visited);
    }
}
