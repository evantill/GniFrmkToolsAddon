package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.components.*;

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
    public void visit(AdapterConnection visited) {
        proxy.visit(visited);
    }

    @Override
    public void visit(AdapterListener visited) {
        proxy.visit(visited);
    }

    @Override
    public void visit(AdapterNotification visited) {
        proxy.visit(visited);
    }

    @Override
    public void visit(Port visited) {
        proxy.visit(visited);
    }

    @Override
    public void visit(Scheduler visited) {
        proxy.visit(visited);
    }

    @Override
    public void visit(JmsAlias visited) {
        proxy.visit(visited);
    }

    @Override
    public void visit(JmsTrigger visited) {
        proxy.visit(visited);
    }

    @Override
    public void visit(NativeTrigger visited) {
        proxy.visit(visited);
    }

    @Override
    public void visit(IntegrationServerPackage visited) {
        proxy.visit(visited);
    }
}
