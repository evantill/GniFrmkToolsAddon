package com.gni.frmk.tools.addon.invoker.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 09:59
 *
 * @author: e03229
 */
public interface Service<I extends ServiceInput, O extends ServiceOutput> {

    O invoke(I input, ServiceContext context) throws ServiceException;
}
