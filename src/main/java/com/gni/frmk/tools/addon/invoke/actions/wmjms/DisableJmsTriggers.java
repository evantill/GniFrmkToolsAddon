package com.gni.frmk.tools.addon.invoke.actions.wmjms;

import com.gni.frmk.tools.addon.invoke.Action;
import com.gni.frmk.tools.addon.invoke.results.NoResult;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 16:55
 *
 * @author: e03229
 */
public class DisableJmsTriggers implements Action<NoResult> {
    private final List<String> triggerNames;
    private boolean applyChangeAcrossCluster;

    public DisableJmsTriggers(List<String> triggerNames, boolean applyChangeAcrossCluster) {
        this.triggerNames = Collections.unmodifiableList(checkNotNull(triggerNames));
        this.applyChangeAcrossCluster = applyChangeAcrossCluster;
    }

    public DisableJmsTriggers(List<String> triggerNames) {
        this.triggerNames = Collections.unmodifiableList(checkNotNull(triggerNames));
        this.applyChangeAcrossCluster = false;
    }

    public List<String> getTriggerNames() {
        return triggerNames;
    }

    public boolean isApplyChangeAcrossCluster() {
        return applyChangeAcrossCluster;
    }
}
