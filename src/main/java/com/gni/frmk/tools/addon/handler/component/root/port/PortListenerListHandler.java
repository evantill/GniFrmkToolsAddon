package com.gni.frmk.tools.addon.handler.component.root.port;

import com.gni.frmk.tools.addon.action.component.root.port.GetPortDetail;
import com.gni.frmk.tools.addon.action.component.root.port.GetPortState;
import com.gni.frmk.tools.addon.action.component.root.port.PortListenerIdList;
import com.gni.frmk.tools.addon.action.component.root.port.PortListenerList;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.component.art.AbstractComponentListHandler;
import com.gni.frmk.tools.addon.model.Component.Type;
import com.gni.frmk.tools.addon.model.component.Port;
import com.gni.frmk.tools.addon.model.component.Port.Detail;
import com.gni.frmk.tools.addon.model.component.id.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 18:09
 *
 * @author: e03229
 */
public class PortListenerListHandler
        extends AbstractComponentListHandler<PackageAndStringId, Detail, ActivableState, Port, PortListenerList, InvokeContext>
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
    protected Action<ComponentDetailResult<Detail>> newGetComponentDetailAction(PackageAndStringId id) {
        return new GetPortDetail(id);
    }

    @Override
    protected Action<ComponentStateResult<ActivableState>> newGetComponentStateAction(PackageAndStringId id) {
        return new GetPortState(id);
    }

    @Override
    protected Port newComponent(PackageAndStringId id, Detail detail, ActivableState state) throws DispatchException {
        Port component = new Port();
        component.setType(Type.PORT);
        component.setId(id);
        component.setDetail(detail);
        component.setCurrentState(state);
        return component;
    }
}
