package com.gni.frmk.tools.addon.command.action.wm.root;

import com.gni.frmk.tools.addon.command.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.NativeTrigger;
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
        implements Action<ListResult<NativeTrigger>>,
        UpdatableCollectionAction<NativeTrigger, List<NativeTrigger>, ListResult<NativeTrigger>> {

    private final List<NativeTrigger> triggers = Lists.newArrayList();

    @Override
    public void setCollection(Collection<NativeTrigger> collection) {
        triggers.addAll(collection);
    }

    @Override
    public List<NativeTrigger> getCollection() {
        return Collections.unmodifiableList(triggers);
    }

    @Override
    public boolean isUpdate() {
        return triggers.size() > 0;
    }
}
