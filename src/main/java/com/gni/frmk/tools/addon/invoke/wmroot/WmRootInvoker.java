package com.gni.frmk.tools.addon.invoke.wmroot;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.configuration.components.IntegrationServerPackage;
import com.gni.frmk.tools.addon.invoke.AbstractWmInvoker;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionException;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.wmroot.GetAllServiceStats.Result;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 20:03
 *
 * @author: e03229
 */
public class WmRootInvoker extends AbstractWmInvoker {

    public WmRootInvoker(IntegrationServerUtil util, InvokeContext context, String toolsPackageName) {
        super("WmRoot", util, context);
        addHandler(new GetPackageListInvoker());
        addHandler(new GetAllServiceStatsInvoker(toolsPackageName));
    }

    public Set<IntegrationServerPackage> getPackageList(GetPackageList param) throws ActionException {
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
