package com.gni.frmk.tools.addon.configuration.components;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.gni.frmk.tools.addon.configuration.components.ComponentState.ComponentStateStatus.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 16:32
 *
 * @author: e03229
 */
@XmlRootElement
public class SchedulerState extends EnableState {
    public static enum SchedulerStatus {
        UNEXPIRED, EXPIRED
    }

    @XmlElement
    private final SchedulerStatus scheduled;

    public SchedulerState(EnableStatus enabled, SchedulerStatus scheduled) {
        super(enabled);
        this.scheduled = scheduled;
    }

    private SchedulerState(){
        super();
        scheduled=null;
    }

    public SchedulerStatus getScheduled() {
        return scheduled;
    }

    @Override
    public ComponentStateStatus getComponentStatus() {
        ComponentStateStatus enableStatus = super.getComponentStatus();
        switch (scheduled) {
            case UNEXPIRED:
                return enableStatus.composeWith(ON);
            case EXPIRED:
                return enableStatus.composeWith(OFF);
            default:
                return enableStatus.composeWith(UNKNOWN);
        }
    }


}
