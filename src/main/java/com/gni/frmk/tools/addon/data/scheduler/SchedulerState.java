package com.gni.frmk.tools.addon.data.scheduler;

import com.gni.frmk.tools.addon.data.component.ComponentState;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 25/11/10
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerState extends ComponentState<SchedulerState> {
    public static enum SchedulerStatus {
        UNEXPIRED, EXPIRED;
    }

    private SchedulerStatus schedulerStatus;

    public SchedulerState(EnableStatus enableStatus, SchedulerStatus schedulerStatus) {
        super(enableStatus);
        this.schedulerStatus = schedulerStatus;
    }

    public SchedulerStatus getSchedulerStatus() {
        return schedulerStatus;
    }

    public void setSchedulerStatus(SchedulerStatus schedulerStatus) {
        this.schedulerStatus = schedulerStatus;
    }

    @Override
    public int compareTo(SchedulerState other) {
        return ComparisonChain.start()
                .compare(getEnableStatus(), other.getEnableStatus())
                .compare(getSchedulerStatus(), other.getSchedulerStatus())
                .result();
    }
}
