package com.gni.frmk.tools.addon.invoke.handler.wmroot;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.ServiceInvokeException;
import com.gni.frmk.tools.addon.invoke.action.wmroot.GetAllServiceStats;
import com.gni.frmk.tools.addon.invoke.action.wmroot.GetAllServiceStats.Result;
import com.gni.frmk.tools.addon.invoke.action.wmroot.WaitServicesEnd;
import com.gni.frmk.tools.addon.invoke.handler.InvokeHandler;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 15:46
 *
 * @author: e03229
 */
public class WaitServicesEndHandler implements InvokeHandler<WaitServicesEnd, NoResult> {

    private final GetAllServiceStatsHandler getAllServiceStatsHandler = new GetAllServiceStatsHandler();

    @Override
    public Class<WaitServicesEnd> getActionType() {
        return WaitServicesEnd.class;
    }

    @Override
    public NSName getService() {
        return getAllServiceStatsHandler.getService();
    }

    @Override
    public NoResult execute(WaitServicesEnd action, InvokeContext context) throws ServiceInvokeException {
        long maxSecondsToWait = action.getMaxSecondsToWait();
        long timeoutTime = System.currentTimeMillis() + (maxSecondsToWait * 1000);
        final GetAllServiceStats getAllServiceStats;
        if (action.hasFilter()) {
            getAllServiceStats = new GetAllServiceStats(action.getFilter());
        } else {
            getAllServiceStats = new GetAllServiceStats();
        }
        Result initialResult = getAllServiceStatsHandler.execute(getAllServiceStats, context);
        long nbrRunning = initialResult.getNbrRunningServices();
        for (long currentTime = System.currentTimeMillis();
                currentTime < timeoutTime; currentTime = System.currentTimeMillis()) {
            Result intermediateResult = getAllServiceStatsHandler.execute(getAllServiceStats, context);
            nbrRunning = intermediateResult.getNbrRunningServices();
            if (nbrRunning > 0) {
                try {
                    Thread.sleep(action.getDelayBetweenTest());
                } catch (InterruptedException ignore) {
                    ignore.printStackTrace();
                }
            } else {
                break;
            }
        }
        if (nbrRunning > 0) {
            throw new ServiceInvokeException(context, action, getService(), null, String.format("waitServicesEnd timeout : still %s service(s) running", nbrRunning));
        }
        return NoResult.newInstance();
    }
}
