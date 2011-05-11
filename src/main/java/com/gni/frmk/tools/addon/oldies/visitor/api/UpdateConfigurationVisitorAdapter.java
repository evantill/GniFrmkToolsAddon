package com.gni.frmk.tools.addon.oldies.visitor.api;

import com.gni.frmk.tools.addon.model.*;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.visitor.api.ConfigurationVisitorRaisingException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class UpdateConfigurationVisitorAdapter<T extends Throwable> implements UpdateConfigurationVisitor, InvocationHandler {

    private final UpdateConfigurationVisitorRaisingException decorated;
    private final UpdateConfigurationVisitor proxy;

    public UpdateConfigurationVisitorAdapter(UpdateConfigurationVisitorRaisingException decorated) {
        this.decorated = decorated;
        proxy = (UpdateConfigurationVisitor) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{UpdateConfigurationVisitor.class},
                this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("visit".equals(method.getName())) {
            return invokeVisitMethod(o, method, objects);
        } else {
            return method.invoke(o, objects);
        }
    }

    private Object invokeVisitMethod(Object o, Method method, Object[] objects) throws Throwable {
        Object result = null;
        Component visited = (Component) objects[0];
        try {
            statusStarted(visited);
            Method realMethod = ConfigurationVisitorRaisingException.class.getMethod(method.getName(), method.getParameterTypes());
            result = realMethod.invoke(decorated, objects);
            statusCompleted(visited);
        } catch (InvocationTargetException ie) {
            statusFailed(visited, ie.getTargetException());
        }
        return result;
    }

    protected abstract void statusCompleted(Component visited);

    protected abstract void statusStarted(Component visited);

    protected abstract void statusFailed(Component visited, Throwable e);

    @Override
    public void visitComponent(AdapterConnection visited) {
        proxy.visitComponent(visited);
    }

    @Override
    public void visitComponent(AdapterListener visited) {
        proxy.visitComponent(visited);
    }

    @Override
    public void visitComponent(AdapterNotification visited) {
        proxy.visitComponent(visited);
    }

    @Override
    public void visitComponent(Port visited) {
        proxy.visitComponent(visited);
    }

    @Override
    public void visitComponent(Scheduler visited) {
        proxy.visitComponent(visited);
    }

    @Override
    public void visitComponent(JmsAlias visited) {
        proxy.visitComponent(visited);
    }

    @Override
    public void visitComponent(JmsTrigger visited) {
        proxy.visitComponent(visited);
    }

    @Override
    public void visitComponent(NativeTrigger visited) {
        proxy.visitComponent(visited);
    }
}
