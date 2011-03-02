package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 27/10/10
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public class ReplayServiceInvokerBuilder extends ServiceInvokerBuilder {

    final PipelineTestUtils testUtils;

    public ReplayServiceInvokerBuilder(IntegrationServerUtil util, String serviceName, PipelineTestUtils testUtils) {
        super(util, serviceName);
        this.testUtils = testUtils;
    }

    @Override
    public ServiceInvoker build() {
        return new ReplayServiceInvoker(this);
    }
}
