package com.gni.frmk.tools.addon.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.action.component.root.scheduler.GetSchedulerDetail;
import com.gni.frmk.tools.addon.action.component.root.scheduler.GetSchedulerState;
import com.gni.frmk.tools.addon.action.component.root.scheduler.SchedulerIdList;
import com.gni.frmk.tools.addon.action.component.root.scheduler.SchedulerList;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.component.art.AbstractComponentListHandler;
import com.gni.frmk.tools.addon.model.Component.Type;
import com.gni.frmk.tools.addon.model.component.Scheduler;
import com.gni.frmk.tools.addon.model.component.Scheduler.Detail;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 18:15
 *
 * @author: e03229
 */
public class SchedulerListHandler
        extends AbstractComponentListHandler<StringId, Detail, SchedulerState, Scheduler, SchedulerList, InvokeContext>
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
    protected Action<ComponentDetailResult<Detail>> newGetComponentDetailAction(StringId id) {
        return new GetSchedulerDetail(id);
    }

    @Override
    protected Action<ComponentStateResult<SchedulerState>> newGetComponentStateAction(StringId id) {
        return new GetSchedulerState(id);
    }

    @Override
    protected Scheduler newComponent(StringId id, Detail detail, SchedulerState state) throws DispatchException {
        Scheduler component = new Scheduler();
        component.setType(Type.SCHEDULER);
        component.setId(id);
        component.setDetail(detail);
        component.setCurrentState(state);
        return component;
    }
}
