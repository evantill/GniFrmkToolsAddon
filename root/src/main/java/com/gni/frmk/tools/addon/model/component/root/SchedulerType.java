package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 16:19
 *
 * @author: e03229
 */
@XmlRootElement
public class SchedulerType
        extends BaseComponentType<SchedulerType, Scheduler,
        StringId, SchedulerState, SchedulerDetail> {

    public static final SchedulerType TYPE = new SchedulerType();

    private SchedulerType() {
        super(Scheduler.class, StringId.class, SchedulerState.class, SchedulerDetail.class);
    }

    @Override
    public boolean isInput() {
        return true;
    }

    @Override
    public boolean isOutput() {
        return false;
    }

    @Override
    public Scheduler.Builder componentBuilder() {
        return Scheduler.builder();
    }

    @Override
    public StringId.Builder idBuilder() {
        return StringId.builder();
    }

    @Override
    public SchedulerState.Builder stateBuilder() {
        return SchedulerState.builder();
    }

    @Override
    public SchedulerDetail.Builder detailBuilder() {
        return SchedulerDetail.builder();
    }

    public static SchedulerType newInstance() {
        return TYPE;
    }
}
