package com.gni.frmk.tools.addon.command.handler.wm.art;

import com.gni.frmk.tools.addon.command.action.StringAction;
import com.gni.frmk.tools.addon.command.api.Result;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 18:31
 *
 * @author: e03229
 */
public abstract class AdapterTypeAwareHandler<A extends StringAction<R>, R extends Result>
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
