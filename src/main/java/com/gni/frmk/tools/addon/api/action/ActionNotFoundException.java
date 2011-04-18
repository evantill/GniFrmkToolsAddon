package com.gni.frmk.tools.addon.api.action;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 13:52
 *
 * @author: e03229
 */
public class ActionNotFoundException extends ActionException {

    public ActionNotFoundException(Action action) {
        super(action, "service not found");
    }

}
