package com.gni.frmk.tools.addon.invoke;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 13 oct. 2010
 * Time: 15:41:27
 * To change this template use File | Settings | File Templates.
 */
public class ServiceRuntimeException extends RuntimeException {

    private final String serviceName;
    private final String causeMessage;

    public ServiceRuntimeException(String serviceName, String causeMessage) {
        this.serviceName = serviceName;
        this.causeMessage = causeMessage;
    }

    public ServiceRuntimeException(String serviceName, RuntimeException ex) {
        super(ex);
        this.serviceName = serviceName;
        causeMessage = ex.getMessage();
    }

    @Override
    public String getMessage() {
        return String.format("ServiceRuntimeException on %s cause: %s", serviceName, causeMessage);
    }
}
