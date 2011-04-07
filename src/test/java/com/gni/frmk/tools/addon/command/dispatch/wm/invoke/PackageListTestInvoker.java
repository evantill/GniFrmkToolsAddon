package com.gni.frmk.tools.addon.command.dispatch.wm.invoke;

import com.gni.frmk.tools.addon.command.action.wm.root.ispackage.PackageList;
import com.gni.frmk.tools.addon.command.api.ActionException;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.util.AbstractTestInvoker;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeException;
import com.gni.frmk.tools.addon.command.handler.wm.root.ispackage.PackageListHandler;
import com.gni.frmk.tools.addon.command.result.SetResult;
import com.gni.frmk.tools.addon.model.component.IntegrationServerPackage;
import junit.framework.Assert;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 16:16
 *
 * @author: e03229
 */
public class PackageListTestInvoker extends AbstractTestInvoker<PackageList, SetResult<IntegrationServerPackage>> {

    public void testGetPackageList() throws ActionException, InvokeException {
        SetResult<IntegrationServerPackage> r = execute();
        Assert.assertEquals(5, r.getCollection().size());
    }

    @Override
    protected ActionHandler<PackageList, SetResult<IntegrationServerPackage>, InvokeContext> getActionHandler() {
        return new PackageListHandler();
    }

    @Override
    protected PackageList getAction() {
        return new PackageList();
    }

}
