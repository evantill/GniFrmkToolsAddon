package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.invoker.module.InvokeServiceModule;
import com.gni.frmk.tools.addon.invoker.service.test.Module1TestService;
import com.gni.frmk.tools.addon.model.component.test.Component1;
import com.gni.frmk.tools.addon.model.module.ModelModule;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.test.ChangeComponent1StateStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.test.GetComponent1DetailStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.test.GetComponent1StateStrategy;
import com.gni.frmk.tools.addon.operation.handler.component.test.ListComponent1IdsStrategy;
import com.gni.frmk.tools.addon.operation.module.ActionContextModule;
import com.gni.frmk.tools.addon.operation.module.ActionHandlerModule;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 18:23
 *
 * @author: e03229
 */
public class TestModuleResource extends AbstractModule {

    private static final TypeLiteral<ListComponentIdsStrategy<?, ?>> LIST_IDS_STRATEGY_TYPE = new TypeLiteral<ListComponentIdsStrategy<?, ?>>() {
    };
    private static final TypeLiteral<GetComponentDetailStrategy<?, ?, ?>> GET_COMPONENT_DETAIL_STRATEGY_TYPE = new TypeLiteral<GetComponentDetailStrategy<?, ?, ?>>() {
    };
    private static final TypeLiteral<GetComponentStateStrategy<?, ?, ?>> GET_COMPONENT_STATE_STRATEGY_TYPE = new TypeLiteral<GetComponentStateStrategy<?, ?, ?>>() {
    };
    private static final TypeLiteral<ChangeComponentStateStrategy<?, ?, ?>> CHANGE_COMPONENT_STATE_STRATEGY_TYPE = new TypeLiteral<ChangeComponentStateStrategy<?, ?, ?>>() {
    };

    @Override
    protected void configure() {
        install(new InvokeServiceModule() {
            @Override
            protected void registerServices() {
                registerService(Module1TestService.class);
            }
        });
        install(new ModelModule() {
            @Override
            protected void registerModelContextPackages() {
                registerModelContextPackage(Component1.class.getPackage());
            }
        });
        install(new ActionContextModule<ListComponentIdsStrategy<?, ?>>(LIST_IDS_STRATEGY_TYPE) {
            @Override
            protected void registerStrategies() {
                registerStrategy(ListComponent1IdsStrategy.class);
            }
        });
        install(new ActionHandlerModule() {
            @Override
            protected void registerActionHandlers() {
                registerActionHandler(ListComponentIdsHandler.class);
            }
        });
        install(new ActionContextModule<GetComponentDetailStrategy<?, ?, ?>>(GET_COMPONENT_DETAIL_STRATEGY_TYPE) {
            @Override
            protected void registerStrategies() {
                registerStrategy(GetComponent1DetailStrategy.class);
            }
        });
        install(new ActionHandlerModule() {
            @Override
            protected void registerActionHandlers() {
                registerActionHandler(GetComponentDetailHandler.class);
            }
        });
        install(new ActionContextModule<GetComponentStateStrategy<?, ?, ?>>(GET_COMPONENT_STATE_STRATEGY_TYPE) {
            @Override
            protected void registerStrategies() {
                registerStrategy(GetComponent1StateStrategy.class);
            }
        });
        install(new ActionHandlerModule() {
            @Override
            protected void registerActionHandlers() {
                registerActionHandler(GetComponentStateHandler.class);
            }
        });
        install(new ActionContextModule<ChangeComponentStateStrategy<?, ?, ?>>(CHANGE_COMPONENT_STATE_STRATEGY_TYPE) {
            @Override
            protected void registerStrategies() {
                registerStrategy(ChangeComponent1StateStrategy.class);
            }

        });
        install(new ActionHandlerModule() {

            @Override
            protected void registerActionHandlers() {
                registerActionHandler(ChangeComponentStateHandler.class);
            }
        });
    }
}
