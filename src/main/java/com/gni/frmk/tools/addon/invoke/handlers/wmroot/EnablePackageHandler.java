package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.EnablePackage;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
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
