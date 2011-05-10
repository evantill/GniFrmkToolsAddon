package com.gni.frmk.tools.addon.action.wm.root.service;

import ev.frmk.tools.plateform.api.action.Action;
import com.gni.frmk.tools.addon.result.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 15:45
 *
 * @author: e03229
 */
public class WaitServicesEnd implements Action<NoResult> {
    private final String filter;
    private final long maxSecondsToWait;
    private final long delayBetweenTest;

    public WaitServicesEnd(String filter, long maxSecondsToWait, long delayBetweenTest) {
        this.filter = filter;
        this.maxSecondsToWait = maxSecondsToWait;
        this.delayBetweenTest = delayBetweenTest;
    }

    public String getFilter() {
        return filter;
    }

    public boolean hasFilter() {
        return filter != null;
    }

    public long getMaxSecondsToWait() {
        return maxSecondsToWait;
    }

    public long getDelayBetweenTest() {
        return delayBetweenTest;
    }
}
