package com.gni.frmk.tools.addon.model.component.state;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 16:32
 *
 * @author: e03229
 */
public class SchedulerState extends EnableState {
    public static enum SchedulerStatus {
        UNKNONW, UNEXPIRED, EXPIRED
    }

    private SchedulerStatus scheduled;

    public SchedulerState() {
    }

    public SchedulerState(EnableStatus enabled, SchedulerStatus scheduled) {
        super(enabled);
        this.scheduled = scheduled;
    }

    public SchedulerStatus getScheduled() {
        return scheduled;
    }

    public void setScheduled(SchedulerStatus scheduled) {
        this.scheduled = scheduled;
    }
}
