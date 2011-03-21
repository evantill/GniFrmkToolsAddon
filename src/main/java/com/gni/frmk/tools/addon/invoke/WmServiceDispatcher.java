package com.gni.frmk.tools.addon.invoke;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/03/11
 * Time: 17:03
 *
 * @author: e03229
 */
public class WmServiceDispatcher extends InvokeDispatcher{

    private final InvokeServiceRegistry registry=new InvokeServiceRegistry();

    @Override
    protected InvokeServiceRegistry getHandlerRegistry() {
        return registry;
    }

    @Override
    protected InvokeContext getInvokeContext() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
