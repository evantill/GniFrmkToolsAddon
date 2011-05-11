package com.gni.frmk.tools.addon.handler.component.root.service;

import com.gni.frmk.tools.addon.action.component.root.service.GetAllServiceStats;
import com.gni.frmk.tools.addon.action.component.root.service.GetAllServiceStats.Result;
import com.gni.frmk.tools.addon.action.component.root.service.WaitServicesEnd;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInvokeException;
import com.gni.frmk.tools.addon.handler.InvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
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
            final String message = String.format("waitServicesEnd timeout : still %s service(s) running", nbrRunning);
            throw new ServiceInvokeException(context, action, getService(), message);
        }
        return NoResult.newInstance();
    }
}
