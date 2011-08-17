package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.google.common.collect.Maps;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 15:47
 *
 * @author: e03229
 */
public class CommandDecoratorFactory {

    private static class MethodSignature {
        private String name;
        private Class[] parameters;

        private MethodSignature(Method method) {
            this.name = method.getName();
            this.parameters = method.getParameterTypes();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MethodSignature that = (MethodSignature) o;

            if (!name.equals(that.name)) return false;
            if (!Arrays.equals(parameters, that.parameters)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    private static class CommandDecoratorHandler implements InvocationHandler {
        private final Command decoratated;
        private final CommandDecorator decorator;
        private final Map<MethodSignature, Method> interceptedMethods = Maps.newHashMap();

        private CommandDecoratorHandler(Command decoratated, CommandDecorator decorator) {
            this.decoratated = decoratated;
            this.decorator = decorator;
            for (Method method : decorator.getClass().getDeclaredMethods()) {
                interceptedMethods.put(new MethodSignature(method), method);
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            MethodSignature key = new MethodSignature(method);
            if (interceptedMethods.containsKey(key)) {
                return interceptedMethods.get(key).invoke(decorator, args);
            } else {
                return method.invoke(decoratated, args);
            }
        }
    }

    public static Command createDecoratorFor(Command decorated, CommandDecorator decorator) {
        CommandDecoratorHandler handler = new CommandDecoratorHandler(decorated, decorator);
        ClassLoader classLoader = decorated.getClass().getClassLoader();
        return (Command) Proxy.newProxyInstance(classLoader, decorator.getDecoratedInterfaces(), handler);
    }
}
