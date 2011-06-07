package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:37
 *
 * @author: e03229
 */
public class ListComponentIdsContext {

    private final Map<ComponentType<?, ?, ?, ?, ?>,ListComponentIdsStrategy<?>> strategies = Maps.newHashMap();

    public <I extends ComponentId<I>> void registerStrategy(ComponentType<?, ?, I, ?, ?> type, ListComponentIdsStrategy<I> strategy){
        strategies.put(type,strategy);
    }

    /**
     *
     * @param type
     * @param <I>
     * @return
     *
     * casting is safe due to registerStrategy method signature.
     */
    @SuppressWarnings("unchecked")
    protected <I extends ComponentId<I>> ListComponentIdsStrategy<I> selectStrategy(ComponentType<?, ?, I, ?, ?> type) {
        ListComponentIdsStrategy<?> strategy = strategies.get(type);
        return (ListComponentIdsStrategy<I>) strategy;
    }

    public <I extends ComponentId<I>> Set<I> listIds(ComponentType<?, ?, I, ?, ?> type, InvokeContext context) throws ServiceException {
        return selectStrategy(type).listIds(context);
    }

}
