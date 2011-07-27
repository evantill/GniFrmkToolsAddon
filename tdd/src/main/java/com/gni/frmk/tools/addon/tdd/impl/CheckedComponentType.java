package com.gni.frmk.tools.addon.tdd.impl;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.api.SingletonComponentType;

import static java.lang.String.format;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 17:33
 *
 * @author: e03229
 */
public abstract class CheckedComponentType<C extends Component<C, S>, I extends ComponentId<I>, S extends ComponentState<S>>
        extends SingletonComponentType<C, I, S> {

    private class CheckObjectType<U, T extends U> {
        private final Class<T> checkedType;

        private CheckObjectType(Class<T> checkedType) {
            this.checkedType = checkedType;
        }

        private boolean accept(U value) {
            return checkedType.isInstance(value);
        }

        private T check(U value) {
            if (accept(value)) {
                return checkedType.cast(value);
            } else {
                throw new IllegalStateException(format("object %s is not of type %s", value, checkedType));
            }
        }
    }

    private CheckObjectType<Component, C> checkComponent;
    private CheckObjectType<ComponentId, I> checkId;
    private CheckObjectType<ComponentState, S> checkState;

    protected CheckedComponentType(Class<C> componentType, Class<I> idType, Class<S> stateType) {
        checkComponent = new CheckObjectType<Component, C>(componentType);
        checkId = new CheckObjectType<ComponentId, I>(idType);
        checkState = new CheckObjectType<ComponentState, S>(stateType);
    }

    @Override
    public C check(Component component) {
        return checkComponent.check(component);
    }

    @Override
    public boolean accept(Component component) {
        return checkComponent.accept(component);
    }

    @Override
    public I check(ComponentId id) {
        return checkId.check(id);
    }

    @Override
    public boolean accept(ComponentId id) {
        return checkId.accept(id);
    }

    @Override
    public S check(ComponentState state) {
        return checkState.check(state);
    }

    @Override
    public boolean accept(ComponentState state) {
        return checkState.accept(state);
    }

}
