package com.gni.frmk.tools.addon.invoker.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:01
 *
 * @author: e03229
 */
public class ServiceNotFoundException extends ServiceException {

    public ServiceNotFoundException(Service<?, ?> service) {
        super(service, String.format("service %s not found", service.getName()));

    }
}
