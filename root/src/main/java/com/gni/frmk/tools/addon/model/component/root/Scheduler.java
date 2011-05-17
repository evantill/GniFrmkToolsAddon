package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.component.root.Scheduler.SchedulerDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
public class Scheduler
        extends BaseComponent<StringId, SchedulerState, SchedulerDetail> {

    public static class SchedulerDetail extends BaseComponent.AbstractDetail {
        private String schedulerType;
        private String name;
        private String service;
        private String description;

        public SchedulerDetail() {
        }

        public SchedulerDetail(String schedulerType, String name, String service, String description) {
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