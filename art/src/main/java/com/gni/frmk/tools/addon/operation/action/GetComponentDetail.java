package com.gni.frmk.tools.addon.operation.action;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

import javax.xml.soap.Detail;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:36
 *
 * @author: e03229
 */
public class GetComponentDetail<I extends ComponentId, D extends Detail>
        implements Action<SingleResult<D>> {
    private final ComponentType type;
    private final ComponentId id;

    public GetComponentDetail(ComponentType type, ComponentId id) {
        this.type = type;
        this.id = id;
    }

    public ComponentType getType() {
        return type;
    }

    public ComponentId getId() {
        return id;
    }
}
