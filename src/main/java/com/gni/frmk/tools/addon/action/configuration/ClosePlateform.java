package com.gni.frmk.tools.addon.action.configuration;

import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 15:10
 *
 * @author: e03229
 */
public class ClosePlateform extends ConfigurationAction<ConfigurationResult> {
    private final String filter;
    private final long maxSecondsToWait;
    private final long delayBetweenTest;

    public ClosePlateform(ImmutableConfiguration configuration, String filter, long maxSecondsToWait, long delayBetweenTest) {
        super(configuration);
        this.filter = filter;
        this.maxSecondsToWait = maxSecondsToWait;
        this.delayBetweenTest = delayBetweenTest;
    }

    public String getFilter() {
        return filter;
    }

    public long getMaxSecondsToWait() {
        return maxSecondsToWait;
    }

    public long getDelayBetweenTest() {
        return delayBetweenTest;
    }
}