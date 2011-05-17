package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.dispatcher.ActionHandlerRegistry;
import com.gni.frmk.tools.addon.operation.dispatcher.ActionHandlerRegistry.HandlerRegistration;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 15:05
 *
 * @author: e03229
 */
public class ModuleManager<C extends ExecutionContext> {

    private final ConcurrentMap<Class<? extends ModuleResource>, ModuleResource<C>> modules = Maps.newConcurrentMap();
    private final ConcurrentMap<Class<? extends ModuleResource>, Set<HandlerRegistration>> moduleRegistrations = Maps.newConcurrentMap();

    private JAXBContext context;
    private final ActionHandlerRegistry<C> handlerRegistry = new ActionHandlerRegistry<C>();

    public static final class ModuleRegistration<C extends ExecutionContext> {
        private final ModuleManager<C> manager;
        private final ModuleResource<C> module;

        public ModuleRegistration(ModuleManager<C> manager, ModuleResource<C> module) {
            this.manager = manager;
            this.module = module;
        }

        public void unloadModule() {
            manager.unloadModule(module);
        }
    }

    public synchronized ModuleRegistration loadModule(ModuleResource<C> module) {
        ModuleResource<C> oldModule = modules.putIfAbsent(module.getClass(), module);
        if (oldModule != null) {
            unregisterModule(oldModule);
        }
        registerModule(module);
        return new ModuleRegistration<C>(this, module);
    }

    public synchronized void unloadModule(ModuleResource<C> module) {
        modules.remove(module.getClass());
        unregisterModule(module);
    }

    public JAXBContext getContext() {
        if (context == null) {
            return createJaxbContext();
        } else {
            return context;
        }
    }

    private void registerModule(ModuleResource<C> module) {
        registerJaxbContext();
        registerActionHandlers(module);
    }

    private void unregisterModule(ModuleResource<C> module) {
        unregisterJaxbContext();
        unregisterActionHandlers(module);
    }

    private void registerJaxbContext() {
        context = createJaxbContext();
    }

    private void unregisterJaxbContext() {
        context = createJaxbContext();
    }

    private JAXBContext createJaxbContext() {
        StringBuilder contextPathBuilder = new StringBuilder();
        contextPathBuilder.append(getClass().getPackage().getName()).append(':');
        for (ModuleResource resource : modules.values()) {
            contextPathBuilder.append(resource.getContextPath()).append(':');
        }
        try {
            return JAXBContext.newInstance(contextPathBuilder.toString());
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    private void registerActionHandlers(ModuleResource<C> module) {
        final HashSet<HandlerRegistration> registrations = Sets.newHashSet();
        for (ActionHandler<?, ?, C> handler : module.getActionHandlers()) {
            HandlerRegistration registration = handlerRegistry.register(handler);
            moduleRegistrations.putIfAbsent(module.getClass(), registrations).add(registration);
        }
    }

    private void unregisterActionHandlers(ModuleResource<C> module) {
        Set<HandlerRegistration> registrations = moduleRegistrations.remove(module.getClass());
        for (HandlerRegistration registration : registrations) {
            registration.unregister();
        }
    }

}
