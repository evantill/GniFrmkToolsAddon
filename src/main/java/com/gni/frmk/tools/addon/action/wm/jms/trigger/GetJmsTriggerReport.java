package com.gni.frmk.tools.addon.action.wm.jms.trigger;

import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.model.component.ImmutableJmsTrigger;
import com.gni.frmk.tools.addon.model.component.ImmutableJmsTrigger.MutableJmsTrigger;
import com.gni.frmk.tools.addon.result.ListResult;
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
        implements Action<ListResult<MutableJmsTrigger>>,
        UpdatableCollectionAction<MutableJmsTrigger, List<MutableJmsTrigger>, ListResult<MutableJmsTrigger>> {

    private final List<MutableJmsTrigger> triggers = Lists.newArrayList();

    @Override
    public void setCollection(Collection<MutableJmsTrigger> jmsTriggers) {
        triggers.addAll(jmsTriggers);
    }

    @Override
    public List<MutableJmsTrigger> getCollection() {
        return Collections.unmodifiableList(triggers);
    }

    @Override
    public void addToCollection(MutableJmsTrigger element) {
        triggers.add(element);
    }

    @Override
    public boolean isUpdate() {
        return triggers.size() > 0;
    }
}
