package com.gni.frmk.tools.addon.invoker.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:49
 *
 * @author: e03229
 */
public class ServiceContextException extends RuntimeException {
    private final ServiceContext context;

    public ServiceContextException(ServiceContext context, Throwable cause) {
        super(cause);
        this.context = context;
    }

    public ServiceContextException(ServiceContext context, String message) {
        super(message);
        this.context = context;
    }

    public ServiceContextException(ServiceContext context, String message, Throwable cause) {
        super(message, cause);
        this.context = context;
    }
}
