package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 16:32
 *
 * @author: e03229
 */
public class SchedulerState extends AbstractState<SchedulerState> {

    private EnableStatus enabled;
    private SchedulerStatus scheduled;

    public SchedulerState() {
        super(false);
        enabled = EnableStatus.UNKNOWN;
        scheduled = SchedulerStatus.UNKNONW;
    }

    public SchedulerState(EnableStatus enabled, SchedulerStatus scheduled) {
        super(enabled == EnableStatus.UNKNOWN || scheduled == SchedulerStatus.UNKNONW);
        this.enabled = enabled;
        this.scheduled = scheduled;
    }

    public SchedulerStatus getScheduled() {
        return scheduled;
    }

    public void setScheduled(SchedulerStatus scheduled) {
        this.scheduled = scheduled;
    }

    public EnableStatus getEnabled() {
        return enabled;
    }

    public void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isUnknown() {
        return enabled == EnableStatus.UNKNOWN || scheduled == SchedulerStatus.UNKNONW;
    }

    @Override
    public int compareTo(SchedulerState other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(getEnabled(), other.getEnabled())
                              .compare(getScheduled(), other.getScheduled())
                              .result();
    }
}
