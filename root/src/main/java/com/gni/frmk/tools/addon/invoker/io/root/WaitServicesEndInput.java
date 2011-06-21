package com.gni.frmk.tools.addon.invoker.io.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInput;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 14:16
 *
 * @author: e03229
 */
public class WaitServicesEndInput implements ServiceInput {
    private final long maxSecondsToWait;
    private final long delayBetweenTests;
    private final boolean throwExceptionIfStillRunning;

    public WaitServicesEndInput(long maxSecondsToWait, long delayBetweenTests, boolean throwExceptionIfStillRunning) {
        this.maxSecondsToWait = maxSecondsToWait;
        this.delayBetweenTests = delayBetweenTests;
        this.throwExceptionIfStillRunning = throwExceptionIfStillRunning;
    }

    public long getMaxSecondsToWait() {
        return maxSecondsToWait;
    }

    public long getDelayBetweenTests() {
        return delayBetweenTests;
    }

    public boolean isThrowExceptionIfStillRunning() {
        return throwExceptionIfStillRunning;
    }
}
