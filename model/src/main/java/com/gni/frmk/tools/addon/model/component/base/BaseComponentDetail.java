package com.gni.frmk.tools.addon.model.component.base;

import com.gni.frmk.tools.addon.model.component.ComponentDetail;

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

    public static abstract class Builder<B extends Builder<B, T>, T extends BaseComponentDetail>
            implements com.gni.frmk.tools.addon.model.component.Builder<B, T> {
    }
}
