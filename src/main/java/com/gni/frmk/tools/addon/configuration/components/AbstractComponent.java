package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.AbstractComponent.AbstractComponentId;
import com.gni.frmk.tools.addon.configuration.components.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.configuration.components.ComponentDetail.Value;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlValue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 13:02
 *
 * @author: e03229
 */
public abstract class AbstractComponent<I extends AbstractComponentId, S extends AbstractComponentState>
        implements Component {

    @NotNull
    @XmlElementRef
    private final I componentId;

    @NotNull
    @XmlElement
    private final ComponentType type;

    @NotNull
    @XmlElementRef
    private final S state;

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

    public ComponentType getType() {
        return type;
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends AbstractComponent<I, S>, I extends AbstractComponentId, S extends AbstractComponentState>
            implements ComponentBuilder<T, B> {
        protected I id;
        protected S state;
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

        public abstract T self();

        public abstract B build();

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
        private final @XmlValue String rawValue;

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
