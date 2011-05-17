package com.gni.frmk.tools.addon.operation.action.component.root.scheduler;

import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;
import com.gni.frmk.tools.addon.model.component.root.Scheduler.SchedulerDetail;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.operation.action.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:40
 *
 * @author: e03229
 */
public class SchedulerFactory implements ComponentFactory<StringId, SchedulerState, SchedulerDetail, Scheduler> {

    @Override
    public Scheduler newComponent(StringId id, SchedulerDetail detail, SchedulerState state) {
        Scheduler component = new Scheduler();
        component.setType(Type.SCHEDULER);
        component.setId(id);
        component.setCurrentState(state);
        component.setDetail(detail);
        return component;
    }

    @Override
    public ListComponentIds<StringId> newListComponentIdsAction() {
        return new ListSchedulerIds();
    }

    @Override
    public GetAllComponents<Scheduler> newGetAllComponentAction() {
        return new GetAllSchedulers();
    }

    @Override
    public GetComponent<Scheduler, StringId> newGetComponentAction(StringId id) {
        return new GetScheduler(id);
    }

    @Override
    public GetComponentDetail<SchedulerDetail, StringId> newGetComponentDetailAction(StringId id) {
        return new GetSchedulerDetail(id);
    }

    @Override
    public GetComponentState<SchedulerState, StringId> newGetComponentStateAction(StringId id) {
        return new GetSchedulerState(id);
    }
}
