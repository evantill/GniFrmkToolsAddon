package com.gni.frmk.tools.addon.operation.action.component.root.scheduler;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 10:38
 *
 * @author: e03229
 */
public class ChangeSchedulerState extends ChangeComponentState<StringId, SchedulerState> {
    public ChangeSchedulerState(SchedulerState newComponentState, Scheduler component) {
        super(newComponentState, component);
    }
}
