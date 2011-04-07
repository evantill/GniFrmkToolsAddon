package com.gni.frmk.tools.addon.command.action.wm.jms;

import com.gni.frmk.tools.addon.command.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.JmsTrigger;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 17:13
 *
 * @author: e03229
 */
public class GetJmsTriggerReport
        implements Action<ListResult<JmsTrigger>>,
        UpdatableCollectionAction<JmsTrigger, List<JmsTrigger>, ListResult<JmsTrigger>> {

    private final List<JmsTrigger> triggers = Lists.newArrayList();

    @Override
    public void setCollection(Collection<JmsTrigger> jmsTriggers) {
        triggers.addAll(jmsTriggers);
    }

    @Override
    public List<JmsTrigger> getCollection() {
        return Collections.unmodifiableList(triggers);
    }

    @Override
    public boolean isUpdate() {
        return triggers.size() > 0;
    }
}
