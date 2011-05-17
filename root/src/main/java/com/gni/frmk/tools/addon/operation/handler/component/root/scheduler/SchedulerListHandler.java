package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.handler.component.AbstractComponentListHandler;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.GetSchedulerDetail;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.GetSchedulerState;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.SchedulerIdList;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.SchedulerList;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;
import com.gni.frmk.tools.addon.model.component.root.Scheduler.SchedulerDetail;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 18:15
 *
 * @author: e03229
 */
public class SchedulerListHandler
        extends AbstractComponentListHandler<StringId, SchedulerDetail, SchedulerState, Scheduler, SchedulerList, InvokeContext>
        implements ActionHandler<SchedulerList, ListResult<Scheduler>, InvokeContext> {
    @Override
    public Class<SchedulerList> getActionType() {
        return SchedulerList.class;
    }

    @Override
    protected Action<ListResult<StringId>> newListIdAction() {
        return new SchedulerIdList();
    }

    @Override
    protected Action<ComponentDetailResult<SchedulerDetail>> newGetComponentDetailAction(StringId id) {
        return new GetSchedulerDetail(id);
    }

    @Override
    protected Action<ComponentStateResult<SchedulerState>> newGetComponentStateAction(StringId id) {
        return new GetSchedulerState(id);
    }

    @Override
    protected Scheduler newComponent(StringId id, SchedulerDetail detail, SchedulerState state) throws DispatchException {
        Scheduler component = new Scheduler();
        component.setType(Type.SCHEDULER);
        component.setId(id);
        component.setDetail(detail);
        component.setCurrentState(state);
        return component;
    }
}
