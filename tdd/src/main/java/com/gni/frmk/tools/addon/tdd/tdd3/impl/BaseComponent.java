package com.gni.frmk.tools.addon.tdd.tdd3.impl;

import com.gni.frmk.tools.addon.tdd.tdd3.api.Component;
import com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentType;

import javax.validation.Validation;
import javax.validation.Validator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 18:17
 *
 * @author: e03229
 */
public abstract class BaseComponent<T extends ComponentType, I extends ComponentId, D> implements Component<D> {

    private final T type;
    private final I id;
    private D data;

    private Validator dataValidator;

    protected BaseComponent(T type, I id, D data) {
        this.type = checkNotNull(type);
        this.id = checkNotNull(id);
        this.data = checkNotNull(data);
        dataValidator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    final public T getType() {
        return type;
    }

    final public I getId() {
        return id;
    }

    final protected D getData() {
        return data;
    }

    @Override
    final public ComponentState getState() {
        ComponentState state = ComponentState.UNKNOWN;
        if (isDataDefined(data)) {
            state = defineStateFromData(data);
        }
        return state;
    }

    protected boolean isDataDefined(D data) {
        return dataValidator.validate(data).isEmpty();
    }

    protected abstract ComponentState defineStateFromData(D data);

    @Override
    final public D save() {
        return cloneData(data);
    }

    @Override
    final public void restore(D saved) {
        data = cloneData(checkNotNull(saved));
    }

    protected abstract D cloneData(D data);
}
