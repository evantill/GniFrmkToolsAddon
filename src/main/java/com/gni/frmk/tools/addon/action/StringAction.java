package com.gni.frmk.tools.addon.action;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:57
 *
 * @author: e03229
 */
public abstract class StringAction<R extends Result> implements Action<R> {

    private final String parameter;

    public StringAction(String parameter) {this.parameter = parameter;}

    public String getParameter() {
        return parameter;
    }
}
