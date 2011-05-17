package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:34
 *
 * @author: e03229
 */
public abstract class ListComponentIds<I extends Id>
        implements Action<ListResult<I>> {
}
