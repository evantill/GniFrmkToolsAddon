package com.gni.frmk.tools.addon.operation.action;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SetResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:49
 *
 * @author: e03229
 */
public class GetAllComponentsByType<C extends Component<?, ?, ?, ?, ?>>
        implements Action<SetResult<C>> {
    private final ComponentType type;

    public GetAllComponentsByType(ComponentType type) {
        this.type = type;
    }

    public ComponentType getType() {
        return type;
    }
}
