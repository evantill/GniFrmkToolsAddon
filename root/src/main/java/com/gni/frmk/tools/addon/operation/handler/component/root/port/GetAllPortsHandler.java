package com.gni.frmk.tools.addon.operation.handler.component.root.port;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.model.component.root.Port.PortDetail;
import com.gni.frmk.tools.addon.operation.action.component.root.port.GetAllPorts;
import com.gni.frmk.tools.addon.operation.action.component.root.port.PortFactory;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.handler.component.GetAllComponentsHandler;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 18:09
 *
 * @author: e03229
 */
public class GetAllPortsHandler
        extends GetAllComponentsHandler<PackageAndStringId, PortDetail, ActivableState, Port, GetAllPorts, InvokeContext>
        implements ActionHandler<GetAllPorts, ListResult<Port>, InvokeContext> {

    public GetAllPortsHandler() {
        super(new PortFactory());
    }

    @Override
    public Class<GetAllPorts> getActionType() {
        return GetAllPorts.class;
    }


}
