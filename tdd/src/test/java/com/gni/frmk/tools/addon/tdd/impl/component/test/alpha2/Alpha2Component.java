package com.gni.frmk.tools.addon.tdd.impl.component.test.alpha2;

import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.api.ComponentStatus;
import com.gni.frmk.tools.addon.tdd.api.RecoverableComponent;
import com.gni.frmk.tools.addon.tdd.impl.component.test.alpha2.Alpha2Component.Alpha2ComponentState;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/08/11
 * Time: 17:51
 *
 * @author: e03229
 */
public class Alpha2Component implements RecoverableComponent<Alpha2Component, Alpha2ComponentState> {

    public static final class Alpha2ComponentState implements ComponentState<Alpha2ComponentState> {

        private final Alpha2ComponentType type;
        private final Alpha2ComponentId id;
        private final ComponentStatus status;

        public Alpha2ComponentState(Alpha2Component component) {
            type = component.type;
            id = component.id;
            status = component.status;
        }

        @Override
        public int compareTo(Alpha2ComponentState other) {
            return ComparisonChain.start()
                                  .compare(type, other.type)
                                  .compare(id, other.id)
                                  .compare(status, other.status)
                                  .result();
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(type, id, status);
        }

        @Override
        public boolean equals(Object obj) {
            if (!getClass().isInstance(obj)) {
                return false;
            }
            int thisHashCode = hashCode();
            int otherHashCode = getClass().cast(obj).hashCode();
            return thisHashCode == otherHashCode;
        }

        public boolean isFrom(Alpha2Component component) {
            int compareTypeAndId = ComparisonChain.start()
                                                  .compare(component.type, type)
                                                  .compare(component.id, id)
                                                  .result();
            return compareTypeAndId == 0;
        }
    }

    private final Alpha2ComponentType type = Alpha2ComponentType.newInstance();
    private final Alpha2ComponentId id;

    private ComponentStatus status = ComponentStatus.UNKNOWN;
    private final ComponentStatus firstDefinedStatus;

    private Alpha2Component(Alpha2ComponentId id, ComponentStatus status) {
        this.id = id;
        this.firstDefinedStatus = status;
    }

    @Override
    public Alpha2ComponentType getType() {
        return type;
    }

    @Override
    public Alpha2ComponentId getId() {
        return id;
    }

    @Override
    public ComponentStatus getStatus() {
        return status;
    }

    @Override
    public Alpha2ComponentState saveState() {
        return new Alpha2ComponentState(this);
    }

    @Override
    public void restoreState(Alpha2ComponentState previousState) {
        if (previousState.isFrom(this)) {
            status = previousState.status;
        } else {
            throw new IllegalStateException("previous state is not from this component");
        }

    }

    @Override
    public void open() {
        if (status.isClosed()) {
            status = ComponentStatus.OPENED;
        } else {
            throw new IllegalStateException("component is not closed : can not open it");
        }
    }

    @Override
    public void close() {
        if (status.isOpened()) {
            status = ComponentStatus.CLOSED;
        } else {
            throw new IllegalStateException("component is not opened : can not close it");
        }
    }

    @Override
    public int compareTo(Alpha2Component o) {
        return id.compareTo(o.getId());
    }

    @Override
    public void refreshStatus() {
        if (status.isUnknown()) {
            status = firstDefinedStatus;
        }
    }

    public static Alpha2Component newInstance(int id, ComponentStatus status) {
        return new Alpha2Component(Alpha2ComponentId.newInstance(id), status);
    }
}
