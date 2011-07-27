package com.gni.frmk.tools.addon.tdd.api;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 15:39
 *
 * @author: e03229
 */
public class DefaultComponentFactory implements ComponentFactory {

    private final Map<Class<? extends ComponentType<?, ?, ?>>, ComponentFactoryDelegate<?, ?, ?>> registry;

    public DefaultComponentFactory(Iterable<ComponentFactoryDelegate<?, ?, ?>> factories) {
        registry = Maps.newHashMap();
        for (ComponentFactoryDelegate<?, ?, ?> delegate : factories) {
            registerComponentFactory(delegate);
        }
    }

    public <T extends ComponentType<C, I, ?>, C extends Component<C, ?>, I extends ComponentId<I>>
    void registerComponentFactory(ComponentFactoryDelegate<T, C, I> factory) {
        Class<T> key = factory.getCreatedComponentType();
        registry.put(key, factory);
    }

    @Override
    public <T extends ComponentType<C, I, ?>, C extends Component<C, ?>, I extends ComponentId<I>>
    C createComponent(T type, I id) {
        return selectFactoryByType(type).createComponent(id);
    }

    @SuppressWarnings({"unchecked"})
    private <T extends ComponentType<C, I, ?>, C extends Component<C, ?>, I extends ComponentId<I>>
    ComponentFactoryDelegate<T, C, I> selectFactoryByType(T type) {
        Class<? extends ComponentType> key = type.getClass();
        //unchecked cast is safe as Delegate define the relation between all the types
        ComponentFactoryDelegate<T, C, I> delegate = (ComponentFactoryDelegate<T, C, I>) registry.get(key);
        if (delegate == null) {
            throw new ComponentFactoryException("component factory not found for component type " + type);
        }
        return delegate;
    }

}
