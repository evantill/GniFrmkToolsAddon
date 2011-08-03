package com.gni.frmk.tools.addon.tdd.visitor;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.google.common.collect.Maps;

import java.lang.ref.WeakReference;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 10:01
 *
 * @author: e03229
 */
public abstract class MultiComponentVisitor<K, V extends ComponentVisitor> implements ComponentVisitor {

    public static class Registration<V extends ComponentVisitor> {
        WeakReference<MultiComponentVisitor<?, V>> registryRef;
        WeakReference<V> delegateRef;

        private Registration(MultiComponentVisitor<?, V> registry, V delegate) {
            this.registryRef = new WeakReference<MultiComponentVisitor<?, V>>(registry);
            this.delegateRef = new WeakReference<V>(delegate);
        }

        public void unregister() {
            MultiComponentVisitor<?, V> registry = registryRef.get();
            V delegate = delegateRef.get();
            if (registry != null && delegate != null) {
                registry.unregisterVisitor(delegate);
            }
        }

        public boolean isRegistred() {
            boolean registred = false;
            MultiComponentVisitor<?, V> registry = registryRef.get();
            V delegate = delegateRef.get();
            if (registry != null && delegate != null) {
                registred = registry.isRegistred(delegate);
            }
            return registred;
        }

        boolean isValid(){
            MultiComponentVisitor<?, V> registry = registryRef.get();
            V delegate = delegateRef.get();
            return registry != null && delegate != null;
        }
    }

    private Map<K, V> delegates = Maps.newHashMap();

    @Override
    public void visitComponent(Component<?, ?> visited) {
        ComponentVisitor delegate = selectDelegate(visited);
        delegate.visitComponent(visited);
    }

    private ComponentVisitor selectDelegate(Component<?, ?> visited) {
        K key = extractKey(visited);
        return delegates.get(key);
    }

    protected abstract K extractKey(Component<?, ?> visited);

    public Registration registerVisitor(V delegate) {
        K key = extractKey(checkNotNull(delegate));
        delegates.put(key, delegate);
        return new Registration<V>(this, delegate);
    }

    protected abstract K extractKey(V delegate);

    private void unregisterVisitor(V registred) {
        K key = extractKey(registred);
        delegates.remove(key);
    }

    private boolean isRegistred(V registred) {
        K key = extractKey(registred);
        return delegates.containsKey(key);
    }

}
