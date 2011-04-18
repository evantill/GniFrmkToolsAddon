package com.gni.frmk.tools.addon.api.action;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 13:52
 *
 * @author: e03229
 */
public class ActionException extends DispatchException {

    private final Action<?> action;

    public ActionException(Action<?> action, String message) {
        super(message);
        this.action = action;
    }

    public ActionException(Action<?> action, Throwable caught) {
        super(caught);
        this.action = action;
    }

    public Action<?> getAction() {
        return action;
    }
}
