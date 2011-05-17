package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:37
 *
 * @author: e03229
 */
public class GetComponent<C extends Component<I, ?, ?>, I extends Id>
        implements Action<SingleResult<C>> {
    private final Type type;
    private final I id;

    public GetComponent(Type type, I id) {
        this.type = type;
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public I getId() {
        return id;
    }
}
