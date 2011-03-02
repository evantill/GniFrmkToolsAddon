package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 27/10/10
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
public class RemoteServiceInvokerBuilder extends ServiceInvokerBuilder {
    RemoteServiceInvokerBuilder(IntegrationServerUtil util, String serviceName) {
        super(util, serviceName);
    }

    @Override
    public RemoteServiceInvoker build() {
        return new RemoteServiceInvoker(this);
    }
}
