package com.gni.frmk.tools.addon.command.action.wm.jms.trigger;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.result.NoResult;

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
public class EnableJmsTriggers implements Action<NoResult> {
    private final List<String> triggerNames;
    private boolean applyChangeAcrossCluster;

    public EnableJmsTriggers(List<String> triggerNames, boolean applyChangeAcrossCluster) {
        this.triggerNames = Collections.unmodifiableList(checkNotNull(triggerNames));
        this.applyChangeAcrossCluster = applyChangeAcrossCluster;
    }

    public EnableJmsTriggers(List<String> triggerNames) {
        this.triggerNames = Collections.unmodifiableList(checkNotNull(triggerNames));
        this.applyChangeAcrossCluster = false;
    }

    public EnableJmsTriggers(String triggerName) {
        this(Collections.singletonList(triggerName));
    }

    public List<String> getTriggerNames() {
        return triggerNames;
    }

    public boolean isApplyChangeAcrossCluster() {
        return applyChangeAcrossCluster;
    }
}
