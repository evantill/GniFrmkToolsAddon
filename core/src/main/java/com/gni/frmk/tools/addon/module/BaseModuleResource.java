package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.invoker.api.Service;
import com.gni.frmk.tools.addon.model.module.ModuleResource;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import java.util.List;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 17:16
 *
 * @author: e03229
 */
public abstract class BaseModuleResource<C extends ExecutionContext>
        extends AbstractModule
        implements ModuleResource {

    private static final TypeLiteral<ActionHandler<?, ?, ?>> ACTION_HANDLER_TYPE = new TypeLiteral<ActionHandler<?, ?, ?>>() {
    };
    private static final TypeLiteral<ListComponentIdsStrategy<?, ?>> LIST_IDS_STRATEGY_TYPE = new TypeLiteral<ListComponentIdsStrategy<?, ?>>() {
    };
    private static final TypeLiteral<GetComponentDetailStrategy<?, ?, ?>> GET_COMPONENT_DETAIL_STRATEGY_TYPE = new TypeLiteral<GetComponentDetailStrategy<?, ?, ?>>() {
    };
    private static final TypeLiteral<GetComponentStateStrategy<?, ?, ?>> GET_COMPONENT_STATE_STRATEGY_TYPE = new TypeLiteral<GetComponentStateStrategy<?, ?, ?>>() {
    };
    private static final TypeLiteral<ChangeComponentStateStrategy<?, ?, ?>> CHANGE_COMPONENT_STATE_STRATEGY_TYPE = new TypeLiteral<ChangeComponentStateStrategy<?, ?, ?>>() {
    };

    private static final Package[] EMPTY_PACKAGES = new Package[0];

    public static final class RegisterContext {
        private List<Package> packages = Lists.newArrayList();
        private List<Class<? extends Service<?, ?>>> invokeServices = Lists.newArrayList();
        private List<Class<? extends ActionHandler<?, ?, ?>>> handlers = Lists.newArrayList();
        private List<Class<? extends ListComponentIdsStrategy<?, ?>>> listComponentIdsStrategies = Lists.newArrayList();
        private List<Class<? extends ChangeComponentStateStrategy<?, ?, ?>>> listChangeComponentStateStrategies = Lists.newArrayList();
        private List<Class<? extends GetComponentDetailStrategy<?, ?, ?>>> listGetComponentDetailStrategies = Lists.newArrayList();
        private List<Class<? extends GetComponentStateStrategy<?, ?, ?>>> listGetComponentStateStrategies = Lists.newArrayList();

        public void addPackage(Package p) {
            packages.add(p);
        }

        public void addInvokeService(Class<? extends Service<?, ?>> service) {
            invokeServices.add(service);
        }

        public void addActionHandler(Class<? extends ActionHandler<?, ?, ?>> handler) {
            handlers.add(handler);
        }

        public void addListComponentIdsStrategy(Class<? extends ListComponentIdsStrategy<?, ?>> strategy) {
            listComponentIdsStrategies.add(strategy);
        }

        public void addChangeComponentStateStrategy(Class<? extends ChangeComponentStateStrategy<?, ?, ?>> strategy) {
            listChangeComponentStateStrategies.add(strategy);
        }

        public void addGetComponentDetailStrategy(Class<? extends GetComponentDetailStrategy<?, ?, ?>> strategy) {
            listGetComponentDetailStrategies.add(strategy);
        }

        public void addGetComponentStateStrategy(Class<? extends GetComponentStateStrategy<?, ?, ?>> strategy) {
            listGetComponentStateStrategies.add(strategy);
        }

        public List<Package> getPackages() {
            return packages;
        }

        public List<Class<? extends Service<?, ?>>> getInvokeServices() {
            return invokeServices;
        }

        public List<Class<? extends ActionHandler<?, ?, ?>>> getHandlers() {
            return handlers;
        }

        public List<Class<? extends ListComponentIdsStrategy<?, ?>>> getListComponentIdsStrategies() {
            return listComponentIdsStrategies;
        }

        public List<Class<? extends ChangeComponentStateStrategy<?, ?, ?>>> getListChangeComponentStateStrategies() {
            return listChangeComponentStateStrategies;
        }

        public List<Class<? extends GetComponentDetailStrategy<?, ?, ?>>> getListGetComponentDetailStrategies() {
            return listGetComponentDetailStrategies;
        }

        public List<Class<? extends GetComponentStateStrategy<?, ?, ?>>> getListGetComponentStateStrategies() {
            return listGetComponentStateStrategies;
        }
    }

    /**
     * must call registerInvokeService() for each invoke service
     *
     * @param context used as a callback
     */
    protected abstract void registerInvokeServices(RegisterContext context);

    /**
     * must call registerModelContextPackage() for each model package
     *
     * @param context used as a callback
     */
    protected abstract void registerModelContextPackages(RegisterContext context);

    /**
     * must call registerActionHandler() for each action handler
     *
     * @param context used as a callback
     */
    protected abstract void registerActionHandlers(RegisterContext context);

    protected abstract void registerListComponentIdsStrategies(RegisterContext context);

    protected abstract void registerChangeComponentStateStrategies(RegisterContext context);

    protected abstract void registerGetComponentDetailStrategies(RegisterContext context);

    protected abstract void registerGetComponentStateStrategies(RegisterContext context);

    protected void registerListComponentIdsStrategy(RegisterContext context, Class<? extends ListComponentIdsStrategy<?, ?>> strategy) {
        context.addListComponentIdsStrategy(strategy);
    }

    protected void registerChangeComponentStateStrategy(RegisterContext context, Class<? extends ChangeComponentStateStrategy<?, ?, ?>> strategy) {
        context.addChangeComponentStateStrategy(strategy);
    }

    protected void registerGetComponentDetailStrategy(RegisterContext context, Class<? extends GetComponentDetailStrategy<?, ?, ?>> strategy) {
        context.addGetComponentDetailStrategy(strategy);
    }

    protected void registerGetComponentStateStrategy(RegisterContext context, Class<? extends GetComponentStateStrategy<?, ?, ?>> strategy) {
        context.addGetComponentStateStrategy(strategy);
    }

    protected void registerInvokeService(RegisterContext context, Class<? extends Service<?, ?>> service) {
        context.addInvokeService(service);
    }

    protected void registerModelContextPackage(RegisterContext context, Package modelContextPackage) {
        context.addPackage(modelContextPackage);
    }

    protected void registerActionHandler(RegisterContext context, Class<? extends ActionHandler<?, ?, ?>> handler) {
        context.addActionHandler(handler);
    }

    @Override
    protected void configure() {
        RegisterContext ctx = new RegisterContext();
        //add model context
        registerModelContextPackages(ctx);
        final Package[] pkgs = ctx.getPackages().toArray(EMPTY_PACKAGES);
        Multibinder<ModelContext> modelContextBinder = newSetBinder(binder(), ModelContext.class);
        modelContextBinder.addBinding().toInstance(new ModelContext() {

            @Override
            public Package[] getModelPackages() {
                return pkgs;
            }
        });
        //add invoke services
        registerInvokeServices(ctx);
        for (Class<? extends Service<?, ?>> service : ctx.getInvokeServices()) {
            bind(service).asEagerSingleton();
        }
        //add action handlers
        registerActionHandlers(ctx);
        Multibinder<ActionHandler<?, ?, ?>> actionHandlerBinder = newSetBinder(binder(), ACTION_HANDLER_TYPE);
        for (Class<? extends ActionHandler<?, ?, ?>> handler : ctx.getHandlers()) {
            actionHandlerBinder.addBinding().to(handler).asEagerSingleton();
        }
        //add listIds strategies
        registerListComponentIdsStrategies(ctx);
        Multibinder<ListComponentIdsStrategy<?, ?>> listIdsStrategiesBinder = newSetBinder(binder(), LIST_IDS_STRATEGY_TYPE);
        for (Class<? extends ListComponentIdsStrategy<?, ?>> strategy : ctx.getListComponentIdsStrategies()) {
            listIdsStrategiesBinder.addBinding().to(strategy).asEagerSingleton();
        }
        //add changeComponent strategies
        registerChangeComponentStateStrategies(ctx);
        Multibinder<ChangeComponentStateStrategy<?, ?, ?>> changeStateStrategiesBinder = newSetBinder(binder(), CHANGE_COMPONENT_STATE_STRATEGY_TYPE);
        for (Class<? extends ChangeComponentStateStrategy<?, ?, ?>> strategy : ctx.getListChangeComponentStateStrategies()) {
            changeStateStrategiesBinder.addBinding().to(strategy).asEagerSingleton();
        }
        //add getDetail strategies
        registerGetComponentDetailStrategies(ctx);
        Multibinder<GetComponentDetailStrategy<?, ?, ?>> getDetailStrategiesBinder = newSetBinder(binder(), GET_COMPONENT_DETAIL_STRATEGY_TYPE);
        for (Class<? extends GetComponentDetailStrategy<?, ?, ?>> strategy : ctx.getListGetComponentDetailStrategies()) {
            getDetailStrategiesBinder.addBinding().to(strategy).asEagerSingleton();
        }
        //add getState strategies
        registerGetComponentStateStrategies(ctx);
        Multibinder<GetComponentStateStrategy<?, ?, ?>> getStateStrategiesBinder = newSetBinder(binder(), GET_COMPONENT_STATE_STRATEGY_TYPE);
        for (Class<? extends GetComponentStateStrategy<?, ?, ?>> strategy : ctx.getListGetComponentStateStrategies()) {
            getStateStrategiesBinder.addBinding().to(strategy).asEagerSingleton();
        }
    }
}
