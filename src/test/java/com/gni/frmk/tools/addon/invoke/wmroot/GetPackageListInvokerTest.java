package com.gni.frmk.tools.addon.invoke.wmroot;

import com.gni.frmk.tools.addon.configuration.component.IntegrationServerPackage;
import com.gni.frmk.tools.addon.dispatcher.ActionException;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.invoke.action.wmroot.PackageList;
import com.gni.frmk.tools.addon.invoke.handler.wmroot.PackageListHandler;
import com.gni.frmk.tools.addon.dispatcher.SetResult;
import com.gni.frmk.tools.addon.invoke.AbstractInvokerTest;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.InvokeException;
import junit.framework.Assert;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 16:16
 *
 * @author: e03229
 */
public class GetPackageListInvokerTest extends AbstractInvokerTest<PackageList, SetResult<IntegrationServerPackage>> {

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
