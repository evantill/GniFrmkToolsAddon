package com.gni.frmk.tools.addon.operation.module;

import com.gni.frmk.tools.addon.operation.handler.component.ActionContext;
import com.gni.frmk.tools.addon.operation.handler.component.ActionStrategy;
import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import java.util.List;
import java.util.Set;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 19:31
 *
 * @author: e03229
 */
public abstract class ActionContextModule<S extends ActionStrategy<?>>
        extends AbstractModule {

    private final TypeLiteral<S> strategyType;
    private final List<Class<? extends S>> strategies = Lists.newArrayList();

    protected ActionContextModule(TypeLiteral<S> strategyType) {
        this.strategyType = strategyType;
    }

    protected abstract void registerStrategies();

    protected void registerStrategy(Class<? extends S> strategy) {
        strategies.add(strategy);
    }

    @Override
    protected void configure() {
        registerStrategies();
        Multibinder<S> binder = newSetBinder(binder(), strategyType);
        for (Class<? extends S> strategy : strategies) {
            binder.addBinding().to(strategy).asEagerSingleton();
        }
    }

    @Provides
    public ActionContext<S> provideActionContext(Set<S> strategies) {
        ActionContext<S> context = ActionContext.newContext();
        for (S strategy : strategies) {
            context.registerStrategy(strategy);
        }
        return context;
    }

    public TypeLiteral<S> getStrategyType() {
        return strategyType;
    }
}
