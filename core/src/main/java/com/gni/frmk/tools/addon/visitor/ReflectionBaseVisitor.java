package com.gni.frmk.tools.addon.visitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.common.base.Objects.firstNonNull;
import static java.lang.String.format;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/05/11
 * Time: 08:42
 *
 * @author: e03229
 */
public abstract class ReflectionBaseVisitor {

    private static final String EMPTY_STRING = "";

    protected void visit(Object visited) {
        visit(visited, EMPTY_STRING);
    }

    protected void visit(Object visited, String prefix) {
        Class<?> visitedClass = visited.getClass();
        final String methodName = format("visit%s%s", firstNonNull(prefix, EMPTY_STRING), visitedClass.getSimpleName());
        Method method = null;
        try {
            method = getClass().getMethod(methodName, visitedClass);
        } catch (NoSuchMethodException ignore) {
            throw new IllegalStateException("can not find visit method " + methodName, ignore);
        }
        try {
            method.invoke(this, visited);
        } catch (InvocationTargetException rethrown) {
            throw new IllegalStateException(rethrown);
        } catch (IllegalAccessException ignore) {
            throw new IllegalStateException(format("can not acces visit method %s", method.getName()), ignore);
        }
    }

}
