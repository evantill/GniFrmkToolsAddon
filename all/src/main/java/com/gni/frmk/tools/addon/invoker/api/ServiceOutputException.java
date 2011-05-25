package com.gni.frmk.tools.addon.invoker.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:11
 *
 * @author: e03229
 */
public class ServiceOutputException extends ServiceException {
    public ServiceOutputException(Service<?, ?> service, Exception cause) {
        super(service, cause);
    }

    public ServiceOutputException(Service<?, ?> service, String message) {
        super(service, message);
    }
}
