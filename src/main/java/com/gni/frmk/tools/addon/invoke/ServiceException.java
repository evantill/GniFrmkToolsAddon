package com.gni.frmk.tools.addon.invoke;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 13 oct. 2010
 * Time: 15:41:19
 * To change this template use File | Settings | File Templates.
 */
public class ServiceException extends Exception {

    private final String serviceName;
    private final String causeMessage;

    public ServiceException(String serviceName, String causeMessage) {
        this.serviceName = serviceName;
        this.causeMessage = causeMessage;
    }

    public ServiceException(String serviceName, Exception ex) {
        super(ex);
        this.serviceName = serviceName;
        causeMessage = ex.getMessage();
    }

    @Override
    public String getMessage() {
        return String.format("ServiceException on %s cause: %s", serviceName, causeMessage);
    }

}
