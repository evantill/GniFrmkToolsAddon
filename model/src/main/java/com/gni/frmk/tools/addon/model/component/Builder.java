package com.gni.frmk.tools.addon.model.component;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 10:32
 *
 * @author: e03229
 */
public interface Builder<B extends  Builder<B,T>,T> {
    T build();
    B validate();
    B self();
}
