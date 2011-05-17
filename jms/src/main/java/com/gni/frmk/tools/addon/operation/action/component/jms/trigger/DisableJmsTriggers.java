package com.gni.frmk.tools.addon.operation.action.component.jms.trigger;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.unmodifiableList;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 16:55
 *
 * @author: e03229
 */
public class DisableJmsTriggers implements Action<NoResult> {
    private final List<StringId> triggerIds;
    private final boolean applyChangeAcrossCluster;

    public DisableJmsTriggers(List<StringId> triggerNames, boolean applyChangeAcrossCluster) {
        this.triggerIds = unmodifiableList(checkNotNull(triggerNames));
        this.applyChangeAcrossCluster = applyChangeAcrossCluster;
    }

    public DisableJmsTriggers(List<StringId> triggerNames) {
        this.triggerIds = unmodifiableList(checkNotNull(triggerNames));
        this.applyChangeAcrossCluster = false;
    }

    public DisableJmsTriggers(StringId triggerName) {
        this(Collections.singletonList(triggerName));
    }

    public List<StringId> getTriggerIds() {
        return triggerIds;
    }

    public List<String> getTriggerNames() {
        List<String> names = Lists.newArrayList();
        for (StringId id : getTriggerIds()) {
            names.add(id.getValue());
        }
        return names;
    }

    public boolean isApplyChangeAcrossCluster() {
        return applyChangeAcrossCluster;
    }
}
