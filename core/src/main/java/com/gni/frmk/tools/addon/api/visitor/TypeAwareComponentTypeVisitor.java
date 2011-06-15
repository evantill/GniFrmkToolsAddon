package com.gni.frmk.tools.addon.api.visitor;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.api.visitor.MethodBuilder.MethodInvocationBuilder;
import com.google.common.collect.Maps;

import java.util.Map;

import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:59
 *
 * @author: e03229
 */
public abstract class TypeAwareComponentTypeVisitor<T extends VisitorContext>
        implements ComponentTypeVisitor<T> {

    private static final Map<ComponentType, MethodInvocationBuilder<Void>> invokers = Maps.newHashMap();

    @Override
    public void visitComponentType(ComponentType visited, T context) {
        if (!invokers.containsKey(visited)) {
            String componentTypeName = visited.getTypeComponent()
                                              .getSimpleName();
            MethodInvocationBuilder<Void> invoker = new MethodBuilder()
                    .where(getClass())
                    .parameterTypes(ComponentType.class, context.getClass())
                    .methodName("visitComponentType", UPPER_UNDERSCORE.to(UPPER_CAMEL, componentTypeName))
                    .compile(Void.class);
            invokers.put(visited, invoker);
        }
        invokers.get(visited).invokeNoCheckedExceptionException(this, visited, context);
    }

}
