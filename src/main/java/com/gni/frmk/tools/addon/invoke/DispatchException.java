package com.gni.frmk.tools.addon.invoke;

/**
* Created by IntelliJ IDEA.
* Date: 17/03/11
* Time: 13:53
*
* @author: e03229
*/
public class DispatchException extends ActionException {
    public DispatchException(Action action, Throwable cause) {
        super(action, cause);
    }

    public DispatchException(Action action, String message) {
        super(action, message);
    }
}
