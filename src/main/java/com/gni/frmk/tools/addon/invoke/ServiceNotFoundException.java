package com.gni.frmk.tools.addon.invoke;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 13 oct. 2010
 * Time: 15:38:44
 * To change this template use File | Settings | File Templates.
 */
public class ServiceNotFoundException extends ServiceRuntimeException {

    public ServiceNotFoundException(String serviceName) {
        super(serviceName, "service not found");
    }

}
