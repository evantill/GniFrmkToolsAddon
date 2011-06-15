package com.gni.frmk.tools.addon.operation.handler.component.root.service;

import com.gni.frmk.tools.addon.operation.action.component.root.service.GetRunningServices;
import com.gni.frmk.tools.addon.operation.action.component.root.service.WaitServicesEnd;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 15:46
 *
 * @author: e03229
 */
public class WaitServicesEndHandler
        implements ActionHandler<WaitServicesEnd, NoResult, ExecutionContext> {

    @Override
    public Class<WaitServicesEnd> getActionType() {
        return WaitServicesEnd.class;
    }

    @Override
    public NoResult execute(WaitServicesEnd action, ExecutionContext context) throws ActionException {
        Dispatcher dispatcher = context.getDispatcher();
        long maxSecondsToWait = action.getMaxSecondsToWait();
        long timeoutTime = System.currentTimeMillis() + (maxSecondsToWait * 1000);
        final GetRunningServices service;
        if (action.hasFilter()) {
            service = new GetRunningServices(action.getFilter());
        } else {
            service = new GetRunningServices();
        }
        long nbrRunning = getNbrRunningServices(action, service, dispatcher);
        for (long currentTime = System.currentTimeMillis();
                currentTime < timeoutTime; currentTime = System.currentTimeMillis()) {
            nbrRunning = getNbrRunningServices(action, service, dispatcher);
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
            throw new ActionException(action, message);
        }
        return NoResult.newInstance();
    }

    private int getNbrRunningServices(WaitServicesEnd action, GetRunningServices service, Dispatcher dispatcher) throws ActionException {
        try {
            return dispatcher.execute(service).getNbrRunningServices();
        } catch (DispatchException cause) {
            throw new ActionException(action, cause);
        }
    }

}
