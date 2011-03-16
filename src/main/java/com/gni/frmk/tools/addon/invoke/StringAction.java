package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.ActionPattern.Action;
import com.gni.frmk.tools.addon.invoke.ActionPattern.Result;

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
