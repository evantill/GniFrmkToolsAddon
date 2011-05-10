package com.gni.frmk.tools.addon.action.wm.root.scheduler;

import com.gni.frmk.tools.addon.action.StringAction;
import com.gni.frmk.tools.addon.model.StringId;
import com.gni.frmk.tools.addon.result.NoResult;
import ev.frmk.tools.plateform.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:38
 *
 * @author: e03229
 */
public class SuspendUserTask implements Action<NoResult> {

    private final StringId oid;

    public SuspendUserTask(StringId oid) {
        this.oid = oid;
    }

    public StringId getOid() {
        return oid;
    }
}
