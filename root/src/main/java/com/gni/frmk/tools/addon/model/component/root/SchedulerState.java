package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 16:32
 *
 * @author: e03229
 */
public class SchedulerState extends AbstractState<SchedulerState> {

    private SuspendedStatus suspended;
    private SchedulerStatus scheduled;

    public SchedulerState() {
        super(false);
        suspended = SuspendedStatus.UNKNOWN;
        scheduled = SchedulerStatus.UNKNONW;
    }

    public SchedulerState(SuspendedStatus suspended, SchedulerStatus scheduled) {
        super(suspended == SuspendedStatus.UNKNOWN || scheduled == SchedulerStatus.UNKNONW);
        this.suspended = suspended;
        this.scheduled = scheduled;
    }

    public SchedulerStatus getScheduled() {
        return scheduled;
    }

    public void setScheduled(SchedulerStatus scheduled) {
        this.scheduled = scheduled;
    }

    public SuspendedStatus getSuspended() {
        return suspended;
    }

    public void setSuspended(SuspendedStatus suspended) {
        this.suspended = suspended;
    }

    @Override
    public boolean unknown() {
        return suspended == SuspendedStatus.UNKNOWN || scheduled == SchedulerStatus.UNKNONW;
    }

    @Override
    public int compareTo(SchedulerState other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(getSuspended(), other.getSuspended())
                              .compare(getScheduled(), other.getScheduled())
                              .result();
    }
}
