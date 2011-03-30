package com.gni.frmk.tools.addon.invoke.handler.wmart;

import com.gni.frmk.tools.addon.dispatcher.Result;
import com.gni.frmk.tools.addon.dispatcher.StringAction;
import com.gni.frmk.tools.addon.invoke.handler.AbstractInvokeHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 18:31
 *
 * @author: e03229
 */
abstract class AdapterTypeAwareHandler<A extends StringAction<R>, R extends Result>
        extends AbstractInvokeHandler<A, R> {

    protected AdapterTypeAwareHandler(String serviceName) {
        super(serviceName);
    }

    @Override
    protected final IData prepareInput(A in) {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 in.getParameter()}
        });
    }
}
