package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:37
 *
 * @author: e03229
 */
class ActionContext<S extends ActionStrategy<?>> {

    private final Map<ComponentType<?, ?, ?, ?, ?>, ActionStrategy<?>> strategies = Maps.newHashMap();

    public <T extends S> void registerStrategy(T strategy) {
        strategies.put(strategy.getComponentType(), strategy);
    }

    @SuppressWarnings({"unchecked"})
    public <T extends S> T selectStrategy(ComponentType<?, ?, ?, ?, ?> type) throws ActionException {
        return (T) strategies.get(type);
    }

    public static <S extends ActionStrategy<?>> ActionContext<S> newContext() {
        return new ActionContext<S>();
    }
}
