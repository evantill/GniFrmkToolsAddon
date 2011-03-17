package com.gni.frmk.tools.addon.invoke.handlers.wmart;

import com.gni.frmk.tools.addon.invoke.handlers.AbstractHandler;
import com.gni.frmk.tools.addon.invoke.ActionPattern.Result;
import com.gni.frmk.tools.addon.invoke.actions.StringAction;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 18:31
 *
 * @author: e03229
 */
abstract class AdapterTypeAwareHandler<A extends StringAction<R>, R extends Result>
        extends AbstractHandler<A, R> {

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
