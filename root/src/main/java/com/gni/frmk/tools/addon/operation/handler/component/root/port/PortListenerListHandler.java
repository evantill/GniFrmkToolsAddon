package com.gni.frmk.tools.addon.operation.handler.component.root.port;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.operation.action.component.root.port.GetPortDetail;
import com.gni.frmk.tools.addon.operation.action.component.root.port.GetPortState;
import com.gni.frmk.tools.addon.operation.action.component.root.port.PortListenerIdList;
import com.gni.frmk.tools.addon.operation.action.component.root.port.PortListenerList;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetAllComponentsHandler;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.root.Port.PortDetail;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 18:09
 *
 * @author: e03229
 */
public class PortListenerListHandler
        extends GetAllComponentsHandler<PackageAndStringId, PortDetail, ActivableState, Port, PortListenerList, InvokeContext>
        implements ActionHandler<PortListenerList, ListResult<Port>, InvokeContext> {

    @Override
    public Class<PortListenerList> getActionType() {
        return PortListenerList.class;
    }

    @Override
    protected Action<ListResult<PackageAndStringId>> newListIdAction() {
        return new PortListenerIdList();
    }

    @Override
    protected Action<ComponentDetailResult<PortDetail>> newGetComponentDetailAction(PackageAndStringId id) {
        return new GetPortDetail(id);
    }

    @Override
    protected Action<ComponentStateResult<ActivableState>> newGetComponentStateAction(PackageAndStringId id) {
        return new GetPortState(id);
    }

    @Override
    protected Port newComponent(PackageAndStringId id, PortDetail detail, ActivableState state) throws DispatchException {
        Port component = new Port();
        component.setType(Type.PORT);
        component.setId(id);
        component.setDetail(detail);
        component.setCurrentState(state);
        return component;
    }
}
