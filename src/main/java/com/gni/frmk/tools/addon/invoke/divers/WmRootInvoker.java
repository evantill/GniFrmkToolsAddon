package com.gni.frmk.tools.addon.invoke.divers;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.configuration.components.IntegrationServerPackage;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.GetAllServiceStats;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.PackageList;
import com.gni.frmk.tools.addon.invoke.exceptions.ActionException;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractWmHandler;
import com.gni.frmk.tools.addon.invoke.handlers.wmroot.GetAllServiceStatsHandler;
import com.gni.frmk.tools.addon.invoke.handlers.wmroot.GetPackageListHandler;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.GetAllServiceStats.Result;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 20:03
 *
 * @author: e03229
 */
public class WmRootInvoker extends AbstractWmHandler {

    public WmRootInvoker(IntegrationServerUtil util, InvokeContext context, String toolsPackageName) {
        super("WmRoot", util, context);
        addHandler(new GetPackageListHandler());
        addHandler(new GetAllServiceStatsHandler(toolsPackageName));
    }

    public Set<IntegrationServerPackage> getPackageList(PackageList param) throws ActionException {
        return invoke(param).getCollection();
    }

    public Result getAllServiceStats() throws ActionException {
        return invoke(new GetAllServiceStats());
    }

    public int getNbrRunningServices() throws ActionException {
        return getAllServiceStats().getNbrRunningServices();
    }

    public Set<String> getRunningServices() throws ActionException {
        return getAllServiceStats().getRunningServices();
    }

    public void waitServicesEnd(long maxSecondsToWait) throws ActionException {
        GetAllServiceStats action = new GetAllServiceStats();
        long timeoutTime = System.currentTimeMillis() + (maxSecondsToWait * 1000);
        long nbrRunning = invoke(action).getNbrRunningServices();
        for (long currentTime = System.currentTimeMillis();
                currentTime < timeoutTime; currentTime = System.currentTimeMillis()) {
            nbrRunning = invoke(action).getNbrRunningServices();
            if (nbrRunning > 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignore) {
                    ignore.printStackTrace();
                }
            } else {
                break;
            }
        }
        if (nbrRunning > 0) {
            throw new ActionException(action, String.format("waitServicesEnd timeout : still %s service(s) running", nbrRunning));
        }
    }
}
