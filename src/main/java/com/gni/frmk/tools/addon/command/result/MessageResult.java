package com.gni.frmk.tools.addon.command.result;

import com.gni.frmk.tools.addon.command.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:30
 *
 * @author: e03229
 */
public class MessageResult implements Result {
    private final String message;

    public MessageResult(String message) {this.message = message;}

    public String getMessage() {
        return message;
    }
}