package com.gni.frmk.tools.addon.api.visitor;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 16:52
 *
 * @author: e03229
 */
public abstract class TypeAwareVisitor<K, V extends TypeAwareDelegate, T> {
    private final Map<K, VisitDelegateMethod<K, V, T>> visitComponentMethods = Maps.newHashMap();
    private final String delegateMathodPrefix;

    protected static class VisitDelegateMethod<K, V extends TypeAwareDelegate, T> {
        final K key;
        final V delegate;
        final Method method;

        private VisitDelegateMethod(K key, V delegate, Method method) {
            this.key = key;
            this.delegate = delegate;
            this.method = method;
        }

        static <K, V extends TypeAwareDelegate, T> VisitDelegateMethod<K, V, T> build(K key, V delegate, Method method) {
            return new VisitDelegateMethod<K, V, T>(key, delegate, method);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                          .add("key", key)
                          .add("delegate", delegate)
                          .add("method", method)
                          .toString();
        }

        public void invoke(T visited) {
            try {
                method.invoke(delegate, visited);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    protected TypeAwareVisitor(String delegateMathodPrefix, Iterable<V> delegated) {
        this.delegateMathodPrefix = delegateMathodPrefix;
        init(delegated);
    }

    private void init(Iterable<V> delegates) {
        for (V delegate : delegates) {
            for (Method m : delegate.getClass().getDeclaredMethods()) {
                if (m.getName().startsWith(delegateMathodPrefix)) {
                    final K key = extractKey(m);
                    final VisitDelegateMethod<K, V, T> delegateMethod = VisitDelegateMethod.build(key, delegate, m);
                    if (visitComponentMethods.containsKey(key)) {
                        VisitDelegateMethod<K, V, T> currentDelegateMethod = visitComponentMethods.get(key);
                        String message = String.format("visitComponent method for %s already defined in %s", key, currentDelegateMethod);
                        throw new IllegalStateException(message);
                    } else {
                        visitComponentMethods.put(key, delegateMethod);
                    }
                }
            }
        }
    }

    protected abstract K extractKey(Method m);

    protected VisitDelegateMethod<K, V, T> getDelegate(K key) {
        VisitDelegateMethod<K, V, T> visitDelegateMethod = visitComponentMethods.get(key);
        if (visitDelegateMethod == null) {
            throw new IllegalStateException(String.format("visitorMethod not found for %s", key));
        }
        return visitDelegateMethod;
    }
}
