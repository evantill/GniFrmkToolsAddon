package com.gni.frmk.tools.addon.operation.action.component.root.port;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.model.component.root.Port.PortDetail;
import com.gni.frmk.tools.addon.operation.action.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:34
 *
 * @author: e03229
 */
public class PortFactory implements ComponentFactory<PackageAndStringId, ActivableState, PortDetail, Port> {


    @Override
    public Port newComponent(PackageAndStringId id, PortDetail detail, ActivableState state) {
        Port component = new Port();
        component.setType(Type.PORT);
        component.setId(id);
        component.setCurrentState(state);
        component.setDetail(detail);
        return component;
    }

    @Override
    public ListComponentIds<PackageAndStringId> newListComponentIdsAction() {
        return new ListPortIds();
    }

    @Override
    public GetAllComponents<Port> newGetAllComponentAction() {
        return new GetAllPorts();
    }

    @Override
    public GetComponent<Port, PackageAndStringId> newGetComponentAction(PackageAndStringId id) {
        return new GetPort(id);
    }

    @Override
    public GetComponentDetail<PortDetail, PackageAndStringId> newGetComponentDetailAction(PackageAndStringId id) {
        return new GetPortDetail(id);
    }

    @Override
    public GetComponentState<ActivableState, PackageAndStringId> newGetComponentStateAction(PackageAndStringId id) {
        return new GetPortState(id);
    }
}
