package com.gni.frmk.tools.addon.result;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:30
 *
 * @author: e03229
 */
public class MessageResult extends StringResult {

    public MessageResult(String message) {
        super(message);
    }

    public String getMessage() {
        return getValue();
    }
}
