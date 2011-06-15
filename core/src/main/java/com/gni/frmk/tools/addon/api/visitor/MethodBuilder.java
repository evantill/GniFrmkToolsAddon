package com.gni.frmk.tools.addon.api.visitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 15:10
 *
 * @author: e03229
 */
public class MethodBuilder {

    private static final Class<?>[] NO_PARAMETERS = new Class<?>[0];
    private static final String MISSING_METHOD_NAME = "missing";

    private Class<?> where = getClass();
    private String methodName = MISSING_METHOD_NAME;
    private Class<?>[] parameterTypes = NO_PARAMETERS;

    public MethodBuilder where(Class<?> where) {
        this.where = where;
        return this;
    }

    public MethodBuilder methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public MethodBuilder methodName(String... elements) {
        StringBuilder builder = new StringBuilder();
        for (String elem : elements) {
            builder.append(elem);
        }
        this.methodName = builder.toString();
        return this;
    }

    public MethodBuilder parameterTypes(Class<?>... parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

    public <T> MethodInvocationBuilder<T> compile(Class<T> returnType) {
        try {
            Method m = where.getMethod(methodName, parameterTypes);
            return new MethodInvocationBuilder<T>(m, parameterTypes, returnType);
        } catch (NoSuchMethodException ignore) {
            throw new IllegalStateException("can not find method " + methodName + " on " + where.getName(), ignore);
        }
    }

    public static final class MethodInvocationBuilder<T> {
        private final Method method;
        private final Class<?>[] parameterTypes;
        private final Class<T> returnType;

        public static MethodBuilder builder(){
            return new MethodBuilder();
        }

        private MethodInvocationBuilder(Method method, Class<?>[] parameterTypes, Class<T> returnType) {
            this.method = method;
            this.parameterTypes = parameterTypes;
            this.returnType = returnType;
        }

        public T invokeNoCheckedExceptionException(Object target, Object... params) {
            try {
                return invoke(target, params);
            } catch (InvocationTargetException ignore) {
                throw new IllegalStateException(ignore);
            }
        }

        public T invoke(Object target, Object... params) throws InvocationTargetException {
            try {
                return returnType.cast(method.invoke(target, params));
            } catch (IllegalAccessException ignore) {
                throw new IllegalStateException(ignore);
            }
        }

    }

}
