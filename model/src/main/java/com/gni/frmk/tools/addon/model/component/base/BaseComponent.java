package com.gni.frmk.tools.addon.model.component.base;

import com.gni.frmk.tools.addon.model.BuilderWithValidation;
import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.visitor.api.ComponentVisitor;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:01
 *
 * @author: e03229
 */
@XmlType(propOrder = {"id",
                      "currentState",
                      "detail"})
public abstract class BaseComponent
        <C extends BaseComponent<C, T, I, S, D>,
                T extends BaseComponentType<T, C, I, S, D>,
                I extends BaseComponentId,
                S extends BaseComponentState,
                D extends BaseComponentDetail>
        implements Component<C, T, I, S, D> {

    private T type;
    private I id;
    private S currentState;
    private D detail;

    protected BaseComponent() {
    }

    protected BaseComponent(T type) {
        this.type = type;
    }

    protected BaseComponent(Builder<?, C, T, I, S, D> builder) {
        builder.validate();
        type = builder.type;
        id = builder.id;
        currentState = builder.state;
        detail = builder.detail;
    }

    //@XmlElementRef(type = BaseComponentType.class)
    @XmlTransient
    public T getType() {
        return type;
    }

    private void setType(T type) {
        this.type = type;
    }

    @XmlElementRef(type = BaseComponentId.class)
    public I getId() {
        return id;
    }

    private void setId(I id) {
        this.id = id;
    }

    @XmlElementRef(type = BaseComponentState.class)
    public S getCurrentState() {
        return currentState;
    }

    private void setCurrentState(S currentState) {
        this.currentState = currentState;
    }

    @XmlElementRef(type = BaseComponentDetail.class)
    public D getDetail() {
        return detail;
    }

    private void setDetail(D detail) {
        this.detail = detail;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public int compareTo(C o) {
        return ComparisonChain.start()
                              .compare(type, o.type)
                              .compare(id, o.id)
                              .compare(detail, o.detail)
                              .compare(currentState, o.currentState)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseComponent that = (BaseComponent) o;

        boolean result =  Objects.equal(type, that.type)
               && Objects.equal(id, that.id)
               && Objects.equal(detail, that.detail)
               && Objects.equal(currentState, that.currentState);

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, id, detail, currentState);
    }

    @XmlTransient
    public abstract static class Builder
            <B extends Builder<B, C, T, I, S, D>,
                    C extends BaseComponent<C, T, I, S, D>,
                    T extends BaseComponentType<T, C, I, S, D>,
                    I extends BaseComponentId,
                    S extends BaseComponentState,
                    D extends BaseComponentDetail>
            implements BuilderWithValidation<B, C> {

        protected final T type;
        protected I id;
        protected S state;
        protected D detail;

        @Override
        public B validate() {
            checkNotNull(id);
            checkNotNull(state);
            checkNotNull(detail);
            return self();
        }

        protected Builder(T type) {
            this.type = type;
        }

        public B id(I id) {
            this.id = checkNotNull(id);
            return self();
        }

        public B state(S state) {
            this.state = checkNotNull(state);
            return self();
        }

        public B detail(D detail) {
            this.detail = checkNotNull(detail);
            return self();
        }

    }

}
