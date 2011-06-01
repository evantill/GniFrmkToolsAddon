package com.gni.frmk.tools.addon.model.component.base;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 10:40
 *
 * @author: e03229
 */
public abstract class BaseComponentType
        <T extends BaseComponentType<T, C, I, S, D>,
                C extends BaseComponent<C, T, I, S, D>,
                I extends BaseComponentId,
                S extends BaseComponentState,
                D extends BaseComponentDetail>
        implements ComponentType<T, C, I, S, D> {

    private Class<C> typeComponent;
    private Class<I> typeId;
    private Class<S> typeState;
    private Class<D> typeDetail;

    protected BaseComponentType() {
    }

    protected BaseComponentType(Class<C> typeComponent, Class<I> typeId, Class<S> typeState, Class<D> typeDetail) {
        this.typeComponent = typeComponent;
        this.typeId = typeId;
        this.typeState = typeState;
        this.typeDetail = typeDetail;
    }

    @Override
    public Class<C> getTypeComponent() {
        return typeComponent;
    }

    @Override
    public Class<I> getTypeId() {
        return typeId;
    }

    @Override
    public Class<S> getTypeState() {
        return typeState;
    }

    @Override
    public Class<D> getTypeDetail() {
        return typeDetail;
    }

    @Override
    public C checkComponent(Component<?, ?, ?, ?, ?> component) {
        return typeComponent.cast(component);
    }

    @Override
    public I checkId(ComponentId id) {
        return typeId.cast(id);
    }

    @Override
    public S checkState(ComponentState state) {
        return typeState.cast(state);
    }

    @Override
    public D checkDetail(ComponentDetail detail) {
        return typeDetail.cast(detail);
    }

    @Override
    public int compareTo(T o) {
        return ComparisonChain.start()
                              .compare(getTypeComponent().getName(), o.getTypeComponent().getName())
                              .result();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(typeComponent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseComponentType that = (BaseComponentType) o;

        return Objects.equal(typeComponent, that.typeComponent);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                      .add("typeComponent", typeComponent)
                      .toString();
    }
}
