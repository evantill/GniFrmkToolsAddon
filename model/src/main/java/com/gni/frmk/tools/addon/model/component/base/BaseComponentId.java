package com.gni.frmk.tools.addon.model.component.base;

import com.gni.frmk.tools.addon.model.BuilderWithValidation;
import com.gni.frmk.tools.addon.model.component.ComponentId;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 11:24
 *
 * @author: e03229
 */
public abstract class BaseComponentId<I extends BaseComponentId<I>> implements ComponentId<I> {

    protected BaseComponentId(){

    }
    protected BaseComponentId(Builder<?, ? extends BaseComponentId> builder) {
        builder.validate();
    }

      @Override
    public int hashCode() {
        throw new IllegalStateException(String.format("hashCode() must be implemented in class %s", getClass().getName()));
    }

    @Override
    public boolean equals(Object obj) {
        throw new IllegalStateException(String.format("equals() must be implemented in class %s", getClass().getName()));
    }

    @XmlTransient
    protected abstract static class Builder
            <B extends Builder<B, I>,
                    I extends ComponentId>
            implements BuilderWithValidation<B,I> {
    }
}
