package com.gni.frmk.tools.addon.action.wm.root.trigger;

import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.model.component.ImmutableNativeTrigger.MutableNativeTrigger;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:37
 *
 * @author: e03229
 */
public class GetNativeTriggerReport
        implements Action<ListResult<MutableNativeTrigger>>,
        UpdatableCollectionAction<MutableNativeTrigger, List<MutableNativeTrigger>, ListResult<MutableNativeTrigger>> {

    private final List<MutableNativeTrigger> triggers = Lists.newArrayList();

    @Override
    public void setCollection(Collection<MutableNativeTrigger> collection) {
        triggers.addAll(collection);
    }

    @Override
    public List<MutableNativeTrigger> getCollection() {
        return Collections.unmodifiableList(triggers);
    }

    @Override
    public void addToCollection(MutableNativeTrigger element) {
        triggers.add(element);
    }

    @Override
    public boolean isUpdate() {
        return triggers.size() > 0;
    }
}
