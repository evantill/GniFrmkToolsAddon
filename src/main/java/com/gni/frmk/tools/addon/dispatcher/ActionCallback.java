package com.gni.frmk.tools.addon.dispatcher;

import com.gni.frmk.tools.addon.dispatcher.Result;

/**
* Created by IntelliJ IDEA.
* Date: 17/03/11
* Time: 13:56
*
* @author: e03229
*/
public interface ActionCallback<R extends Result> {
    void onFailure(Throwable caught);

    void onSuccess(R result);
}
