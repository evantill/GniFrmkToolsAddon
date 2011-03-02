package com.gni.frmk.tools.addon.data.scheduler;

import com.gni.frmk.tools.addon.data.component.ComponentBuilder;
import com.gni.frmk.tools.addon.data.component.ComponentState;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import static com.gni.frmk.tools.addon.data.component.ComponentType.SCHEDULER;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 18:28:26
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerBuilder extends ComponentBuilder<Scheduler, SchedulerInfo, SchedulerState> {

    public static final class CustomXmlAdapter extends XmlAdapter<ComponentBuilder<Scheduler, SchedulerInfo, SchedulerState>, Scheduler> {
        @Override
        public Scheduler unmarshal(ComponentBuilder<Scheduler, SchedulerInfo, SchedulerState> v) throws Exception {
            return v.build();
        }

        @Override
        public ComponentBuilder<Scheduler, SchedulerInfo, SchedulerState> marshal(Scheduler v) throws Exception {
            return new SchedulerBuilder().define(v);
        }
    }

    String type;
    String name;
    String oid;
    String service;
    String description;
    ComponentState.EnableStatus enableStatus;
    SchedulerState.SchedulerStatus schedulerStatus;

    /**
     * use pipeline data from service wm.server.schedule:getUserTaskList
     *
     * @param doc the datas of one scheduler task
     * @return builder for chain calling
     */
    public ComponentBuilder<Scheduler, SchedulerInfo, SchedulerState> define(IData doc) {
        IDataCursor curDoc = doc.getCursor();
        try {
            type = IDataUtil.getString(curDoc, "type");
            name = IDataUtil.getString(curDoc, "name");
            oid = IDataUtil.getString(curDoc, "oid");
            service = IDataUtil.getString(curDoc, "service");
            description = IDataUtil.getString(curDoc, "description");
            String execState = IDataUtil.getString(curDoc, "execState");
            enableStatus = ComponentState.EnableStatus.valueOf(execState.toUpperCase());
            String schedState = IDataUtil.getString(curDoc, "schedState");
            schedulerStatus = SchedulerState.SchedulerStatus.valueOf(schedState.toUpperCase());
        } finally {
            curDoc.destroy();
        }
        return this;
    }

    public Scheduler build() {
        this.defineType(SCHEDULER)
                .defineId(oid)
                .defineState(new SchedulerState(enableStatus, schedulerStatus))
                .defineInfo(new SchedulerInfo(type, name, service, description));
        return new Scheduler(this);
    }
}
