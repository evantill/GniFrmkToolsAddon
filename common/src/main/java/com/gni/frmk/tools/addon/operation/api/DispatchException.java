package com.gni.frmk.tools.addon.operation.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 13:53
 *
 * @author: e03229
 */
public class DispatchException extends Exception {
    public DispatchException(Throwable cause) {
        super(cause);
    }

    public DispatchException(String message) {
        super(message);
    }

    public DispatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
