package com.gni.frmk.tools.addon.invoke;

/**
* Created by IntelliJ IDEA.
* Date: 17/03/11
* Time: 13:52
*
* @author: e03229
*/
public class ActionRuntimeException extends RuntimeException {

    private final Action<?> action;
    private final String causeMessage;

    public ActionRuntimeException(Action<?> action, String causeMessage) {
        this.action = action;
        this.causeMessage = causeMessage;
    }

    public ActionRuntimeException(Action<?> action, RuntimeException ex) {
        super(ex);
        this.action = action;
        causeMessage = ex.getMessage();
    }

    @Override
    public String getMessage() {
        return String.format("ServiceRuntimeException on %s cause: %s", action.getClass().getName(), causeMessage);
    }
}
