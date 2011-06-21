package com.gni.frmk.tools.addon.operation.module;

import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import java.util.List;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 19:24
 *
 * @author: e03229
 */
public abstract class ActionHandlerModule extends AbstractModule {

    private static final TypeLiteral<ActionHandler<?, ?, ?>> ACTION_HANDLER_TYPE = new TypeLiteral<ActionHandler<?, ?, ?>>() {
    };

    List<Class<? extends ActionHandler<?, ?, ?>>> handlers = Lists.newArrayList();

    protected abstract void registerActionHandlers();

    protected void registerActionHandler(Class<? extends ActionHandler<?, ?, ?>> handler) {
        handlers.add(handler);
    }

    @Override
    protected void configure() {
        registerActionHandlers();
        Multibinder<ActionHandler<?, ?, ?>> actionHandlerBinder = newSetBinder(binder(), ACTION_HANDLER_TYPE);
        for (Class<? extends ActionHandler<?, ?, ?>> handler : handlers) {
            actionHandlerBinder.addBinding().to(handler).asEagerSingleton();
        }
    }
}
