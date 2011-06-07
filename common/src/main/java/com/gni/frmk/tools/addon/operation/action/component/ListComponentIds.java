package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SetResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:33
 *
 * @author: e03229
 */
public class ListComponentIds<I extends ComponentId<I>, T extends ComponentType<T,?,I,?,?>>
        extends TypeAwareAction<T>
        implements Action<SetResult<I>> {

    public ListComponentIds(T type) {
        super(type);
    }
}
