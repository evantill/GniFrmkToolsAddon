package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.Result;
import com.gni.frmk.tools.addon.model.component.ComponentId;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 20:00
 *
 * @author: e03229
 */
public interface ComponentAction<I extends ComponentId,R extends Result> extends Action<R> {
    public I getId();
}
