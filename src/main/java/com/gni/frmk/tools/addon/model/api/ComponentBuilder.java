package com.gni.frmk.tools.addon.model.api;

import com.gni.frmk.tools.addon.BuilderWithValidation;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:46
 *
 * @author: e03229
 */
public interface ComponentBuilder<B extends ComponentBuilder<B,T>, T extends Component> extends BuilderWithValidation<B,T> {
    B from(T source);
}
