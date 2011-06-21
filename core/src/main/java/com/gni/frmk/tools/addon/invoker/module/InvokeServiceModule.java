package com.gni.frmk.tools.addon.invoker.module;

import com.gni.frmk.tools.addon.invoker.api.Service;
import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 19:09
 *
 * @author: e03229
 */
public abstract class InvokeServiceModule extends AbstractModule {
    private List<Class<? extends Service<?, ?>>> services = Lists.newArrayList();

    protected final void registerService(Class<? extends Service<?, ?>> service) {
        services.add(service);
    }

    protected abstract void registerServices();

    @Override
    protected final void configure() {
        registerServices();
        for (Class<? extends Service<?, ?>> service : services) {
            bind(service).asEagerSingleton();
        }
    }
}
