package com.gni.frmk.tools.addon.dispatch.wm.invoke;

import com.gni.frmk.tools.addon.action.wm.root.ispackage.PackageList;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.util.AbstractTestInvoker;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.root.ispackage.PackageListHandler;
import com.gni.frmk.tools.addon.result.SetResult;
import com.gni.frmk.tools.addon.model.component.ImmutableIntegrationServerPackage;
import junit.framework.Assert;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 16:16
 *
 * @author: e03229
 */
public class PackageListTestInvoker extends AbstractTestInvoker<PackageList, SetResult<ImmutableIntegrationServerPackage>> {

    public void testGetPackageList() throws DispatchException {
        SetResult<ImmutableIntegrationServerPackage> r = execute();
        Assert.assertEquals(5, r.getCollection().size());
    }

    @Override
    protected ActionHandler<PackageList, SetResult<ImmutableIntegrationServerPackage>, InvokeContext> getActionHandler() {
        return new PackageListHandler();
    }

    @Override
    protected PackageList getAction() {
        return new PackageList();
    }

}
