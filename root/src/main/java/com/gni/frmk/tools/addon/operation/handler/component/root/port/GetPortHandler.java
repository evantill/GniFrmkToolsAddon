package com.gni.frmk.tools.addon.operation.handler.component.root.port;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.model.component.root.Port.PortDetail;
import com.gni.frmk.tools.addon.operation.action.component.root.port.GetPort;
import com.gni.frmk.tools.addon.operation.action.component.root.port.PortFactory;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 20:00
 *
 * @author: e03229
 */
public class GetPortHandler
        extends GetComponentHandler<PackageAndStringId, PortDetail, ActivableState, Port, GetPort, InvokeContext> {

    public GetPortHandler() {
        super(new PortFactory());
    }

    @Override
    public Class<? extends GetPort> getActionType() {
        return GetPort.class;
    }
}
