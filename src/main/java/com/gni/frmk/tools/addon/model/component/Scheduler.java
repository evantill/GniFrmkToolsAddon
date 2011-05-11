package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.BaseComponent;
import com.gni.frmk.tools.addon.model.component.Scheduler.Detail;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.visitor.TypedComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
public class Scheduler
        extends BaseComponent<StringId, SchedulerState, Detail> {

    @Override
    public void accept(TypedComponentVisitor visitor) {
        visitor.visitScheduler(this);
    }

    public static class Detail extends BaseComponent.AbstractDetail {
        private String schedulerType;
        private String name;
        private String service;
        private String description;

        public Detail() {
        }

        public Detail(String schedulerType, String name, String service, String description) {
            this.schedulerType = schedulerType;
            this.name = name;
            this.service = service;
            this.description = description;
        }

        public String getSchedulerType() {
            return schedulerType;
        }

        public void setSchedulerType(String schedulerType) {
            this.schedulerType = schedulerType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}