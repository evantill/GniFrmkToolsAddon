package com.gni.frmk.tools.addon.operation.handler.component.bytype;

import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/06/11
 * Time: 17:11
 *
 * @author: e03229
 */
public interface ActionOnComponentTypeStrategy<R extends Result, A extends ActionOnComponentType<?, R>, E extends ExecutionContext> {
    R execute(A action, E context) throws ActionException;
}
