package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.ActionPattern.Result;
import com.gni.frmk.tools.addon.invoke.StringAction;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 18:31
 *
 * @author: e03229
 */
abstract class AdapterTypeAwareAction<A extends StringAction<R>,R extends Result>
        extends AbstractInvoker<A,R> {

    protected AdapterTypeAwareAction(String serviceName) {
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
