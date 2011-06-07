package com.gni.frmk.tools.addon.operation.action;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:46
 *
 * @author: e03229
 */
public class GetComponent<I extends ComponentId, C extends Component<?,?,I, ?, ?>>
        implements Action<SingleResult<C>> {
    private final ComponentType type;
    private final I id;

    public GetComponent(ComponentType type, I id) {
        this.type = type;
        this.id = id;
    }

    public ComponentType getType() {
        return type;
    }

    public I getId() {
        return id;
    }
}
