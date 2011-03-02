package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 27/10/10
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public class LocalServiceInvokerBuilder extends ServiceInvokerBuilder {

    public LocalServiceInvokerBuilder(IntegrationServerUtil util, String serviceName) {
        super(util, serviceName);
    }

    @Override
    public LocalServiceInvoker build() {
        return new LocalServiceInvoker(this);
    }


}
