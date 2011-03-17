package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.configuration.components.IntegrationServerPackage;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.GetAllServiceStats;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.GetAllServiceStats.Result;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.PackageList;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.WaitServicesEnd;
import com.gni.frmk.tools.addon.invoke.exceptions.ActionException;
import com.gni.frmk.tools.addon.invoke.exceptions.InvokeException;
import com.gni.frmk.tools.addon.invoke.handlers.wmroot.GetAllServiceStatsHandler;
import com.gni.frmk.tools.addon.invoke.handlers.wmroot.PackageListHandler;
import com.gni.frmk.tools.addon.invoke.handlers.wmroot.WaitServicesEndHandler;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 20:03
 *
 * @author: e03229
 */
public class WmRootInvoker extends AbstractWmHandler {

    private final String toolsPackageName;

    public WmRootInvoker(IntegrationServerUtil util, InvokeContext context, String toolsPackageName) {
        super("WmRoot", util, context);
        this.toolsPackageName = toolsPackageName;
        addHandler(new PackageListHandler());
        addHandler(new GetAllServiceStatsHandler());
        addHandler(new WaitServicesEndHandler(new GetAllServiceStatsHandler()));
    }

    public Set<IntegrationServerPackage> getPackageList(PackageList param) throws ActionException, InvokeException {
        return invoke(param).getCollection();
    }

    public Result getAllServiceStats() throws ActionException, InvokeException {
        return invoke(new GetAllServiceStats(toolsPackageName));
    }

    public int getNbrRunningServices() throws ActionException, InvokeException {
        return getAllServiceStats().getNbrRunningServices();
    }

    public Set<String> getRunningServices() throws ActionException, InvokeException {
        return getAllServiceStats().getRunningServices();
    }

    public void waitServicesEnd(long maxSecondsToWait) throws ActionException, InvokeException {
        invoke(new WaitServicesEnd(toolsPackageName, maxSecondsToWait, 500));
    }
}
