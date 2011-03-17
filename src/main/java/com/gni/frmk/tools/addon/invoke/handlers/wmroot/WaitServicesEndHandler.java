package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.GetAllServiceStats;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.GetAllServiceStats.Result;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.WaitServicesEnd;
import com.gni.frmk.tools.addon.invoke.exceptions.ActionException;
import com.gni.frmk.tools.addon.invoke.exceptions.InvokeException;
import com.gni.frmk.tools.addon.invoke.results.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 15:46
 *
 * @author: e03229
 */
public class WaitServicesEndHandler implements ActionHandler<WaitServicesEnd, NoResult, InvokeContext> {

    private final GetAllServiceStatsHandler getAllServiceStatsHandler;

    public WaitServicesEndHandler(GetAllServiceStatsHandler getAllServiceStatsHandler) {
        this.getAllServiceStatsHandler = getAllServiceStatsHandler;
    }

    @Override
    public Class<WaitServicesEnd> getActionType() {
        return WaitServicesEnd.class;
    }

    @Override
    public NoResult execute(WaitServicesEnd action, InvokeContext context) throws ActionException, InvokeException {
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
            throw new ActionException(action, String.format("waitServicesEnd timeout : still %s service(s) running", nbrRunning));
        }
        return NoResult.newInstance();
    }
}
