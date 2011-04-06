package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.BuilderWithJsr303Validation;
import com.gni.frmk.tools.addon.model.api.ComponentId;
import com.gni.frmk.tools.addon.model.api.ComponentState;
import com.gni.frmk.tools.addon.model.api.Component;
import com.gni.frmk.tools.addon.model.api.ComponentDetail;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentId;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.api.ComponentBuilder;
import com.gni.frmk.tools.addon.model.api.ComponentDetail.Value;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlValue;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 13:02
 *
 * @author: e03229
 */
public abstract class AbstractComponent<I extends AbstractComponentId, S extends AbstractComponentState> implements Component<I, S> {

    @NotNull
    @XmlElementRef
    private final I componentId;

    @NotNull
    @XmlElement
    private final ComponentType type;

    @NotNull
    @XmlElementRef
    private S state;

    @NotNull
    @XmlElementRef
    @XmlElementWrapper
    private final List<AbstractComponentDetail> details;

    protected AbstractComponent(Builder<?, ?, I, S> builder) {
        componentId = builder.id;
        details = Lists.newArrayList(builder.details);
        state = builder.state;
        type = builder.type;
    }

    protected AbstractComponent() {
        componentId = null;
        details = null;
        state = null;
        type = null;
    }

    public I getComponentId() {
        return componentId;
    }

    @Override
    public List<ComponentDetail> getDetails() {
        List<ComponentDetail> returnValue = Lists.newArrayList();
        returnValue.addAll(details);
        return returnValue;
    }

    public S getState() {
        return state;
    }

    @Override
    public void setState(S state) {
        this.state = checkNotNull(state);
    }

    public ComponentType getType() {
        return type;
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends AbstractComponent<I, S>, I extends AbstractComponentId, S extends AbstractComponentState>
            extends BuilderWithJsr303Validation<T,B> implements ComponentBuilder<T, B>
    {
        @NotNull
        protected I id;
        @NotNull
        protected S state;
        @NotNull
        protected ComponentType type;

        protected Set<AbstractComponentDetail> details = Sets.newHashSet();

        public final Builder<T, B, I, S> defineId(I value) {
            id = value;
            return self();
        }

        public final Builder<T, B, I, S> defineDetail(AbstractComponentDetail value) {
            details.add(value);
            return self();
        }

        public final Builder<T, B, I, S> defineDetails(AbstractComponentDetail... values) {
            details.addAll(Arrays.asList(values));
            return self();
        }

        public final Builder<T, B, I, S> defineState(S value) {
            state = value;
            return self();
        }

        public final Builder<T, B, I, S> defineType(ComponentType value) {
            type = value;
            return self();
        }

        //TODO @Override
        public T from(B source) {
            id = source.getComponentId();
            state = source.getState();
            type = source.getType();
            for (ComponentDetail detail : source.getDetails()) {
                details.add((AbstractComponentDetail) detail);
            }
            return self();
        }
    }

    public static abstract class AbstractComponentId implements ComponentId {
        private final
        @XmlValue
        String rawValue;

        protected AbstractComponentId(String rawValue) {
            this.rawValue = rawValue;
        }

        protected AbstractComponentId() {
            this.rawValue = null;
        }

        public String getRawValue() {
            return rawValue;
        }
    }

    public static abstract class AbstractComponentState implements ComponentState {
    }

    public static abstract class AbstractComponentDetail<T extends Value> implements ComponentDetail<T> {
    }

}
