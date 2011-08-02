package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.api.ComponentType;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 17:59
 *
 * @author: e03229
 */
public abstract class BaseTestComponentType<C extends Component<C, S>, I extends ComponentId<I>, S extends ComponentState<S>>
        implements ComponentType<C, I, S> {

    private final Class<C> componentType;
    private final Class<I> componentIdType;
    private final Class<S> componentStateType;

    protected BaseTestComponentType(Class<C> componentType, Class<I> componentIdType, Class<S> componentStateType) {
        this.componentType = componentType;
        this.componentIdType = componentIdType;
        this.componentStateType = componentStateType;
    }

    @Override
    public C check(Component<?, ?> component) {
        return componentType.cast(component);
    }

    @Override
    public boolean accept(Component<?, ?> component) {
        return componentType.isInstance(component);
    }

    @Override
    public I check(ComponentId<?> id) {
        return componentIdType.cast(id);
    }

    @Override
    public boolean accept(ComponentId<?> id) {
        return componentIdType.isInstance(id);
    }

    @Override
    public S check(ComponentState<?> state) {
        return componentStateType.cast(state);
    }

    @Override
    public boolean accept(ComponentState<?> state) {
        return componentStateType.isInstance(state);
    }

    @Override
    public int compareTo(ComponentType o) {
        return ComparisonChain.start()
                              .compare(hashCode(), o.hashCode())
                              .result();
    }
}
