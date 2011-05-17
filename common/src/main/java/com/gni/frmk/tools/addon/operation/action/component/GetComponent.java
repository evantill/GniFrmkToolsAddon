package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:37
 *
 * @author: e03229
 */
public abstract class GetComponent<C extends Component<I, ?, ?>, I extends Id>
        implements Action<SingleResult<C>> {
    private final I id;

    protected GetComponent(I id) {
        this.id = id;
    }

    public I getId() {
        return id;
    }
}
