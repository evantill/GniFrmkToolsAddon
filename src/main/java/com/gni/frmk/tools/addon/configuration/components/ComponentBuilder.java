package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.builder.BuilderWithValidation;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:46
 *
 * @author: e03229
 */
public interface ComponentBuilder<B extends ComponentBuilder<B,T>, T extends Component> extends BuilderWithValidation<B,T> {

//    void build();

    //B self();

    B from(T source);
}
