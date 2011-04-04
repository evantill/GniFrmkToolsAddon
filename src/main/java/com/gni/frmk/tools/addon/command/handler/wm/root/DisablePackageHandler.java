package com.gni.frmk.tools.addon.command.handler.wm.root;

import com.gni.frmk.tools.addon.command.action.wm.root.DisablePackage;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:42
 *
 * @author: e03229
 */
public class DisablePackageHandler extends AbstractInvokeHandler<DisablePackage, NoResult>
        implements ActionHandler<DisablePackage, NoResult, InvokeContext> {

    public DisablePackageHandler() {
        super("pub.packages:disablePackage");
    }

    @Override
    public Class<DisablePackage> getActionType() {
        return DisablePackage.class;
    }

    @Override
    protected IData prepareInput(DisablePackage in) {
        return IDataFactory.create(new Object[][]{
                {"package",
                 in.getPackageName()}
        });
    }

    @Override
    protected NoResult parseOutput(DisablePackage action, IData output) {
        return NoResult.newInstance();
    }
}
