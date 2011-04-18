package com.gni.frmk.tools.addon.action.wm.jms.trigger;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.result.NoResult;

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
    private final List<String> triggerNames;
    private boolean applyChangeAcrossCluster;

    public DisableJmsTriggers(List<String> triggerNames, boolean applyChangeAcrossCluster) {
        this.triggerNames = unmodifiableList(checkNotNull(triggerNames));
        this.applyChangeAcrossCluster = applyChangeAcrossCluster;
    }

    public DisableJmsTriggers(List<String> triggerNames) {
        this.triggerNames = unmodifiableList(checkNotNull(triggerNames));
        this.applyChangeAcrossCluster = false;
    }

    public DisableJmsTriggers(String triggerName) {
        this(Collections.singletonList(triggerName));
    }

    public List<String> getTriggerNames() {
        return triggerNames;
    }

    public boolean isApplyChangeAcrossCluster() {
        return applyChangeAcrossCluster;
    }
}
