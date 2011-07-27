package com.gni.frmk.tools.addon.tdd.impl.integrationserver;

import com.gni.frmk.tools.addon.tdd.impl.CheckedComponentType;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 15:47
 *
 * @author: e03229
 */
public class IntegrationServerType
        extends CheckedComponentType<IntegrationServer, IntegrationServerId, IntegrationServerState> {

    public IntegrationServerType() {
        super(IntegrationServer.class, IntegrationServerId.class, IntegrationServerState.class);
    }

}
