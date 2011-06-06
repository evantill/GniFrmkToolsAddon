package com.gni.frmk.tools.addon.model.component.base;

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

    @XmlTransient
    protected abstract static class Builder
            <B extends Builder<B, I>,
                    I extends ComponentId>
            implements com.gni.frmk.tools.addon.model.component.Builder<B,I> {
    }
}
