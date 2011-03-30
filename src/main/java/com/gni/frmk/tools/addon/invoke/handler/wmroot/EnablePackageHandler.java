package com.gni.frmk.tools.addon.invoke.handler.wmroot;

import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.action.wmroot.EnablePackage;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.handler.AbstractInvokeHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:42
 *
 * @author: e03229
 */
public class EnablePackageHandler extends AbstractInvokeHandler<EnablePackage, NoResult>
        implements ActionHandler<EnablePackage, NoResult, InvokeContext> {

    public EnablePackageHandler() {
        super("pub.packages:enablePackage");
    }

    @Override
    public Class<EnablePackage> getActionType() {
        return EnablePackage.class;
    }

    @Override
    protected IData prepareInput(EnablePackage in) {
        return IDataFactory.create(new Object[][]{
                {"package",
                 in.getPackageName()}
        });
    }

    @Override
    protected NoResult parseOutput(EnablePackage action, IData output) {
        return NoResult.newInstance();
    }
}
