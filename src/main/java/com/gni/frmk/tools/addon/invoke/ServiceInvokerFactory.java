package com.gni.frmk.tools.addon.invoke;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 27/10/10
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
public interface ServiceInvokerFactory {
    ServiceInvokerBuilder createServiceInvokerBuilder(String serviceName);
}
