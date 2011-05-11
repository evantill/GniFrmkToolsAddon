package com.gni.frmk.tools.addon.action.component.root.scheduler;

import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.result.NoResult;
import com.gni.frmk.tools.addon.api.action.Action;

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
