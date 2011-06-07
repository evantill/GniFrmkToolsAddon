package com.gni.frmk.tools.addon.model.component.base;

import com.gni.frmk.tools.addon.model.BuilderWithValidation;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 11:24
 *
 * @author: e03229
 */
public abstract class BaseComponentDetail<T extends BaseComponentDetail<T>> implements ComponentDetail<T> {

    protected BaseComponentDetail(){

    }

    protected BaseComponentDetail(Builder<?,? extends BaseComponentDetail> builder) {
        builder.validate();
    }

    @XmlTransient
    public static abstract class Builder<B extends Builder<B, T>, T extends BaseComponentDetail>
            implements BuilderWithValidation<B, T> {
    }
}
