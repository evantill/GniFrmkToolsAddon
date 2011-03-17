package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.ActionPattern.ExecutionContext;
import com.gni.frmk.tools.addon.invoke.ActionPattern.Result;

/**
* Created by IntelliJ IDEA.
* Date: 17/03/11
* Time: 13:56
*
* @author: e03229
*/
public interface ActionHandler<A extends Action<R>, R extends Result, E extends ExecutionContext> {
    Class<A> getActionType();

    R execute(A action, E context) throws ActionException;

//        void rollback(A action, R result, E context) throws ActionException;
}
