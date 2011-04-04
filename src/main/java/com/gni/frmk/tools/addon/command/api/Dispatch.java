package com.gni.frmk.tools.addon.command.api;

/**
* Created by IntelliJ IDEA.
* Date: 17/03/11
* Time: 13:58
*
* @author: e03229
*/
public interface Dispatch {
       <A extends Action<R>, R extends Result> R execute(A action) throws DispatchException;
}
