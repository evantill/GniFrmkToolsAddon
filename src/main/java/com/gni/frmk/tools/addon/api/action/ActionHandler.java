package com.gni.frmk.tools.addon.api.action;

/**
* Created by IntelliJ IDEA.
* Date: 17/03/11
* Time: 13:56
*
* @author: e03229
*/
public interface ActionHandler<A extends Action<? extends Result>, R extends Result, E extends ExecutionContext> {
    Class<A> getActionType();

    R execute(A action, E context) throws ActionException;

    //TODO ajouter fonction de rollback
//        void rollback(A action, R result, E context) throws ActionException;
}
