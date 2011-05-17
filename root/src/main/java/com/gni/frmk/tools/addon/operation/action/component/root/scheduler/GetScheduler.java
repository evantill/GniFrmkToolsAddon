package com.gni.frmk.tools.addon.operation.action.component.root.scheduler;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:42
 *
 * @author: e03229
 */
public class GetScheduler extends GetComponent<Scheduler, StringId> {
    public GetScheduler(StringId id) {
        super(id);
    }
}
