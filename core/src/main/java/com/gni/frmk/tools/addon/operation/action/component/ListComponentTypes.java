package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SetResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:31
 *
 * @author: e03229
 */
public enum ListComponentTypes implements Action<SetResult<? extends ComponentType<?, ?, ?, ?, ?>>> {
    SINGLETON;

    public static ListComponentTypes newInstance() {
        return SINGLETON;
    }
}
