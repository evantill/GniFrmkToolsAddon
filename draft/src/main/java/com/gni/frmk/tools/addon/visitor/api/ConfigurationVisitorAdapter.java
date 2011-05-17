package com.gni.frmk.tools.addon.visitor.api;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;

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
public abstract class ConfigurationVisitorAdapter<T extends Throwable> implements ConfigurationVisitor, InvocationHandler {

    private final ConfigurationVisitorRaisingException decorated;
    private final ConfigurationVisitor proxy;

    public ConfigurationVisitorAdapter(ConfigurationVisitorRaisingException decorated) {
        this.decorated = decorated;
        proxy = (ConfigurationVisitor) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{ConfigurationVisitor.class},
                this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("visit".equals(method.getName())) {
            return invokeVisitMethod(o, method, objects);
        } else if ("ComponentConfiguration".equals(method.getName())) {
            return invokeVisitComponentConfigurationMethod(o, method, objects);
        } else {
            return method.invoke(o, objects);
        }
    }

    private Object invokeVisitComponentConfigurationMethod(Object o, Method method, Object[] objects) throws Throwable {
        Object result = null;
        ComponentConfiguration visited = (ComponentConfiguration) objects[0];
        try {
            statusStarted(visited.getComponent());
            Method realMethod = ConfigurationVisitorRaisingException.class.getMethod(method.getName(), method.getParameterTypes());
            result = realMethod.invoke(decorated, objects);
            statusCompleted(visited.getComponent());
        } catch (InvocationTargetException ie) {
            statusFailed(visited.getComponent(), ie.getTargetException());
        }
        return result;
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

    //@Override
    public void visitComponentConfiguration(ComponentConfiguration visited) {
        //TODO fix it
        //proxy.visitComponentConfiguration(visited);
    }
}
