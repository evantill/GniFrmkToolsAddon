package com.gni.frmk.tools.addon.invoke.wmroot;

import com.gni.frmk.tools.addon.configuration.components.IntegrationServerPackage;
import com.gni.frmk.tools.addon.invoke.AbstractInvokerTest;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionException;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.SetResult;
import junit.framework.Assert;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 16:16
 *
 * @author: e03229
 */
public class GetPackageListInvokerTest extends AbstractInvokerTest<GetPackageList, SetResult<IntegrationServerPackage>> {

    public void testGetPackageList() throws ActionException {
        SetResult<IntegrationServerPackage> r = execute();
        Assert.assertEquals(5, r.getCollection().size());
    }

    @Override
    protected ActionHandler<GetPackageList, SetResult<IntegrationServerPackage>, InvokeContext> getActionHandler() {
        return new GetPackageListInvoker();
    }

    @Override
    protected GetPackageList getAction() {
        return new GetPackageList();
    }

}
