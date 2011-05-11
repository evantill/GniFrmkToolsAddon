package com.gni.frmk.tools.addon.dispatch.wm.invoke.api;

import com.gni.frmk.tools.addon.handler.InvokeHandler;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/03/11
 * Time: 17:29
 *
 * @author: e03229
 */
public class InvokeServiceRegistryBuilder {
    Map<Class<?>, InvokeHandler<?, ?>> handlers = Maps.newHashMap();

    public InvokeServiceRegistryBuilder addHandler(InvokeHandler handler) {
        InvokeHandler<?, ?> oldest = handlers.put(handler.getClass(), handler);
        assert oldest == null;
        return this;
    }

    public InvokeServiceRegistryBuilder defineServices() {
        Iterable<InvokeHandler> services = load(InvokeHandler.class);
        for (InvokeHandler h : services) {
            addHandler(h);
        }
        return this;
    }

    public InvokeServiceRegistry build() {
        return new InvokeServiceRegistry(handlers.values());
    }

    public <S> Iterable<S> load(Class<S> ifc) {
        try {
            ClassLoader ldr = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> e = ldr.getResources("META-INF/services/" + ifc.getName());
            Collection<S> services = new ArrayList<S>();
            while (e.hasMoreElements()) {
                URL url = e.nextElement();
                InputStream is = url.openStream();
                try {
                    BufferedReader r = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    while (true) {
                        String line = r.readLine();
                        if (line == null)
                            break;
                        int comment = line.indexOf('#');
                        if (comment >= 0)
                            line = line.substring(0, comment);
                        String name = line.trim();
                        if (name.length() == 0)
                            continue;
                        Class<?> clz = Class.forName(name, true, ldr);
                        Class<? extends S> impl = clz.asSubclass(ifc);
                        Constructor<? extends S> ctor = impl.getConstructor();
                        S svc = ctor.newInstance();
                        services.add(svc);
                    }
                } finally {
                    is.close();
                }
            }
            return services;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }


}
