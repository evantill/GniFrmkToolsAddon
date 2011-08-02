package com.gni.frmk.tools.addon.tdd.impl.component;


import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 18:24
 *
 * @author: e03229
 */
public abstract class BaseTestComponentState
        <T extends BaseTestComponentType<?, I, S>, I extends ComponentId<I>, S extends BaseTestComponentState<T, I, S>>
        implements ComponentState<S> {

    private final T componentType;
    private final I componentId;

    protected BaseTestComponentState(T componentType, I componentId) {
        this.componentType = componentType;
        this.componentId = componentId;
    }

    public T getComponentType() {
        return componentType;
    }

    public I getComponentId() {
        return componentId;
    }

    @Override
    public int compareTo(S o) {
        return ComparisonChain.start()
                              .compare(this.getComponentType(), o.getComponentType())
                              .compare(this.getComponentId(), o.getComponentId())
                              .result();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseTestComponentState that = (BaseTestComponentState) o;

        return Objects.equal(getComponentType(), that.getComponentType())
               && Objects.equal(getComponentId(), that.getComponentId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getComponentType(), getComponentId());
    }
}
