package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.api.Service;
import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.SingleValueOutput;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 19:14
 *
 * @author: e03229
 */
public class WaitServicesEnd implements Service<NoInput, SingleValueOutput<Long>> {
    private final long maxSecondsToWait;
    private final long delayBetweenTests;
    private final boolean throwExceptionIfStillRunning;

    private final GetAllServiceStats getAllServiceStats;

    public WaitServicesEnd(long maxSecondsToWait, long delayBetweenTests, boolean throwExceptionIfStillRunning) {
        this.maxSecondsToWait = maxSecondsToWait;
        this.delayBetweenTests = delayBetweenTests;
        this.throwExceptionIfStillRunning = throwExceptionIfStillRunning;
        getAllServiceStats = new GetAllServiceStats();
    }

    public WaitServicesEnd(long maxSecondsToWait, long delayBetweenTests, boolean throwExceptionIfStillRunning, String packageToIgnore) {
        this.maxSecondsToWait = maxSecondsToWait;
        this.delayBetweenTests = delayBetweenTests;
        this.throwExceptionIfStillRunning = throwExceptionIfStillRunning;
        getAllServiceStats = new GetAllServiceStats(packageToIgnore);
    }

    @Override
    public SingleValueOutput<Long> invoke(NoInput input, ServiceContext context) throws ServiceException {
        long timeoutTime = System.currentTimeMillis() + (maxSecondsToWait * 1000);
        long nbrRunning = getAllServiceStats.invoke(NoInput.singleton, context).getValues().size();
        for (long currentTime = System.currentTimeMillis();
                currentTime < timeoutTime; currentTime = System.currentTimeMillis()) {
            nbrRunning = getAllServiceStats.invoke(NoInput.singleton, context).getValues().size();
            if (nbrRunning > 0) {
                try {
                    Thread.sleep(delayBetweenTests);
                } catch (InterruptedException ignore) {
                    ignore.printStackTrace();
                }
            } else {
                break;
            }
        }
        if (nbrRunning > 0 && throwExceptionIfStillRunning) {
            final String message = String.format("waitServicesEnd timeout : still %s service(s) running", nbrRunning);
            throw new ServiceException(this, message);
        }
        return SingleValueOutput.newInstance(nbrRunning);
    }
}
