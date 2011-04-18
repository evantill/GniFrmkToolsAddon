package com.gni.frmk.tools.addon.model.api;

import com.gni.frmk.tools.addon.model.api.ImmutableComponent.MutableComponent;
import com.gni.frmk.tools.addon.model.api.immutable.ImmutableObject;
import com.gni.frmk.tools.addon.model.api.immutable.MutableObject;
import com.gni.frmk.tools.addon.model.component.detail.SimpleDetail;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 15:33
 *
 * @author: e03229
 */
public abstract class ImmutableComponent
        <IC extends ImmutableComponent<IC, MC, I, S>,
                MC extends MutableComponent<IC, MC, I, S>,
                I extends ComponentId,
                S extends ComponentState>
        implements
        ImmutableObject<IC, IC, MC>,
        ReadableComponent<IC, MC, I, S> {

    private final I componentId;

    private final ComponentType type;

    private final S state;

    private final List<SimpleDetail> details;

    protected ImmutableComponent(ReadableComponent<IC, MC, I, S> source) {
        componentId = source.getComponentId();
        type = source.getType();
        state = source.getState();
        details = Lists.newArrayList();
        details.addAll(source.getDetails());
    }

    @Override
    public I getComponentId() {
        return componentId;
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public S getState() {
        return state;
    }

    @Override
    public List<SimpleDetail> getDetails() {
        return Collections.unmodifiableList(details);
    }

    public abstract static class MutableComponent
            <IC extends ImmutableComponent<IC, MC, I, S>,
                    MC extends MutableComponent<IC, MC, I, S>,
                    I extends ComponentId,
                    S extends ComponentState>
            implements
            ReadableComponent.WritableComponent<IC, MC, I, S>,
            MutableObject<IC, IC, MC> {

        private I componentId;

        private ComponentType type;

        private S state;

        private List<SimpleDetail> details;

        protected MutableComponent(ReadableComponent<IC, MC, I, S> source) {
            setComponentId(source.getComponentId());
            setType(source.getType());
            setState(source.getState());
            setDetails(source.getDetails());
        }

        protected MutableComponent() {
        }

        public I getComponentId() {
            return componentId;
        }

        public void setComponentId(I componentId) {
            this.componentId = componentId;
        }

        public ComponentType getType() {
            return type;
        }

        public void setType(ComponentType type) {
            this.type = type;
        }

        public S getState() {
            return state;
        }

        public void setState(S state) {
            this.state = state;
        }

        public List<SimpleDetail> getDetails() {
            return details;
        }

        public void setDetails(List<SimpleDetail> details) {
            List<SimpleDetail> newDetails = Lists.newArrayList();
            newDetails.addAll(details);
            this.details = newDetails;
        }

        @Override
        public void addDetail(SimpleDetail detail) {
            details.add(detail);
        }
    }
}
