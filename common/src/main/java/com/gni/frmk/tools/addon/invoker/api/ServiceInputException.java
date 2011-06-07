package com.gni.frmk.tools.addon.invoker.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:11
 *
 * @author: e03229
 */
public class ServiceInputException extends ServiceException {

    public ServiceInputException(Service<?, ?> service, Exception cause) {
        super(service, cause);
    }
}
