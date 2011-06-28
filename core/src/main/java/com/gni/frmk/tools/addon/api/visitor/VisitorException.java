package com.gni.frmk.tools.addon.api.visitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 18:06
 *
 * @author: e03229
 */
public class VisitorException extends RuntimeException{

    public VisitorException(String message) {
        super(message);
    }

    public VisitorException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisitorException(Throwable cause) {
        super(cause);
    }
}
