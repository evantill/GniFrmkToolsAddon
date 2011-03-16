package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.ActionPattern.Action;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ExecutionContext;
import com.gni.frmk.tools.addon.invoke.ActionPattern.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:07
 *
 * @author: e03229
 */
public interface ActionPattern<A extends Action<R>, R extends Result, E extends ExecutionContext> {

    interface ActionHandler<A extends Action<R>, R extends Result, E extends ExecutionContext> {
        Class<A> getActionType();

        R execute(A action, E context) throws ActionException;

//        void rollback(A action, R result, E context) throws ActionException;
    }

    interface Action<R extends Result> {
    }

    interface Result {
    }

    interface ExecutionContext {
    }

    class ActionException extends Exception {
        private final Action action;

        public ActionException(Action action, Throwable cause) {
            super(cause);
            this.action = action;
        }

        public ActionException(Action action, String message) {
            super(message);
            this.action = action;
        }

        public Action getAction() {
            return action;
        }
    }

    interface ActionCallback<R> {
        void onFailure(Throwable caught);

        void onSuccess(R result);
    }

}
