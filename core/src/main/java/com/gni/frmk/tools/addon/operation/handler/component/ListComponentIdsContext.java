package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.api.strategy.Strategy;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:37
 *
 * @author: e03229
 */
public class ListComponentIdsContext {

    private final Map<ComponentType<?, ?, ?, ?, ?>, ListComponentIdsStrategy<?, ?>> strategies = Maps.newHashMap();

    public <T extends ComponentType<?, ?, I, ?, ?>, I extends ComponentId<?>>
    void registerStrategy(ListComponentIdsStrategy<T, I> strategy) {
        strategies.put(strategy.getType(), strategy);
    }

    public <I extends ComponentId<?>>
    SetResult<I> execute(ListComponentIds<I> action, InvokeContext executionContext) throws ActionException {
        ListComponentIdsStrategy<?, I> strategy = (ListComponentIdsStrategy<?, I>) strategies.get(action.getType());
        return strategy.execute(action, executionContext);
    }
}
