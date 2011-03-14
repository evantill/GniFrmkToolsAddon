package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.gni.frmk.tools.addon.configuration.components.AdapterListener;
import com.gni.frmk.tools.addon.configuration.components.AdapterNotification;
import com.gni.frmk.tools.addon.configuration.components.Component;
import com.gni.frmk.tools.addon.configuration.components.JmsAlias;
import com.gni.frmk.tools.addon.configuration.components.JmsTrigger;
import com.gni.frmk.tools.addon.configuration.components.NativeTrigger;
import com.gni.frmk.tools.addon.configuration.components.Port;
import com.gni.frmk.tools.addon.configuration.components.Scheduler;

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

    public void visit(AdapterConnection visited) {
        proxy.visit(visited);
    }

    public void visit(AdapterListener visited) {
        proxy.visit(visited);
    }

    public void visit(AdapterNotification visited) {
        proxy.visit(visited);
    }

    public void visit(Port visited) {
        proxy.visit(visited);
    }

    public void visit(Scheduler visited) {
        proxy.visit(visited);
    }

    public void visit(JmsAlias visited) {
        proxy.visit(visited);
    }

    public void visit(JmsTrigger visited) {
        proxy.visit(visited);
    }

    public void visit(NativeTrigger visited) {
        proxy.visit(visited);
    }
}
