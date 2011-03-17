package com.gni.frmk.tools.addon.invoke;

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

    interface Result {
    }

    interface ExecutionContext {
    }

    interface Dispatch {
           <A extends Action<R>, R extends Result> R execute( A action ) throws DispatchException;
    }

}
