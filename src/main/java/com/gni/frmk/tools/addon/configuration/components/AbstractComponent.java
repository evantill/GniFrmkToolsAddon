package com.gni.frmk.tools.addon.configuration.components;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.validation.constraints.NotNull;
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
public abstract class AbstractComponent<I extends ComponentId, S extends ComponentState> implements Component {

    @NotNull
    private final I id;

    @NotNull
    private final List<ComponentDetail> details;

    @NotNull
    private final S state;

    @NotNull
    private final ComponentType type;

    protected AbstractComponent(Builder<?, ?, I, S> builder) {
        id = builder.id;
        details = Lists.newArrayList(builder.details);
        state = builder.state;
        type = builder.type;
    }

    public I getId() {
        return id;
    }

    public List<ComponentDetail> getDetails() {
        return Collections.unmodifiableList(details);
    }

    public S getState() {
        return state;
    }

    public ComponentType getType() {
        return type;
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends AbstractComponent<I, S>, I extends ComponentId, S extends ComponentState>
            implements ComponentBuilder<T, B> {
        protected I id;
        protected S state;
        protected ComponentType type;
        protected Set<ComponentDetail> details = Sets.newHashSet();

        public final Builder<T, B, I, S> defineId(I value) {
            id = value;
            return self();
        }

        public final Builder<T, B, I, S> defineDetail(ComponentDetail value) {
            details.add(value);
            return self();
        }

        public final Builder<T, B, I, S> defineDetails(ComponentDetail... values) {
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

        @Override
        public T from(B source) {
            id = source.getId();
            state = source.getState();
            type = source.getType();
            for (ComponentDetail detail : source.getDetails()) {
                details.add(detail);
            }
            return self();
        }
    }
}
