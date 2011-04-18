package com.gni.frmk.tools.addon.handler.wm.root.ispackage;

import com.gni.frmk.tools.addon.action.wm.root.ispackage.EnablePackage;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
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
