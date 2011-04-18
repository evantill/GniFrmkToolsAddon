package com.gni.frmk.tools.addon.action.wm.root.scheduler;

import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.result.ListResult;
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
    public void addToCollection(Scheduler element) {
        schedulers.add(element);
    }

    @Override
    public boolean isUpdate() {
        return schedulers.size() > 0;
    }
}
