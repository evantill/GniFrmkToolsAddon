package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.ActionPattern.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:30
 *
 * @author: e03229
 */
public class MessageResult implements Result{
    private final String message;

    public MessageResult(String message) {this.message = message;}

    public String getMessage() {
        return message;
    }
}
