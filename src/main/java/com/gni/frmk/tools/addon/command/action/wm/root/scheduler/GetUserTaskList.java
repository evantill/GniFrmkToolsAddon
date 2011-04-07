package com.gni.frmk.tools.addon.command.action.wm.root.scheduler;

import com.gni.frmk.tools.addon.command.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.Scheduler;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:38
 *
 * @author: e03229
 */
public class GetUserTaskList
        implements Action<ListResult<Scheduler>>,
        UpdatableCollectionAction<Scheduler, List<Scheduler>, ListResult<Scheduler>> {

    private final List<Scheduler> schedulers = Lists.newArrayList();

    @Override
    public void setCollection(Collection<Scheduler> collection) {
        schedulers.addAll(collection);
    }

    @Override
    public List<Scheduler> getCollection() {
        return Collections.unmodifiableList(schedulers);
    }

    @Override
    public boolean isUpdate() {
        return schedulers.size() > 0;
    }
}
