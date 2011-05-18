package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 15:05
 *
 * @author: e03229
 */
public interface ModuleResource<C extends ExecutionContext> {
    /**
     * used for creation Jaxb context path
     *
     * @return the FQDN of this resource (could use ':' to separate multiple path).
     */
    String getContextPath();

    /**
     * used by HandlerRegistry
     *
     * @return the handlers provided by this module
     */
    List<ActionHandler<?, ?, C>> getActionHandlers();
}
