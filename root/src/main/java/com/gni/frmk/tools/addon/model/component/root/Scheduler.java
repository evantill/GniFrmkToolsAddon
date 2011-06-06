package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
@XmlRootElement
public class Scheduler
        extends BaseComponent<Scheduler, SchedulerType, StringId, SchedulerState, SchedulerDetail> {

    private Scheduler() {
        super(SchedulerType.TYPE);
    }

    public Scheduler(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponent.Builder<Builder, Scheduler, SchedulerType, StringId, SchedulerState, SchedulerDetail> {
        public Builder() {
            super(SchedulerType.newInstance());
        }

        @Override
        public Scheduler build() {
            return new Scheduler(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}