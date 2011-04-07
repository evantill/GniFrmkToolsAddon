package com.gni.frmk.tools.addon.command.action.wm.root.scheduler;

import com.gni.frmk.tools.addon.command.action.StringAction;
import com.gni.frmk.tools.addon.command.result.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:38
 *
 * @author: e03229
 */
public class SuspendUserTask extends StringAction<NoResult> {
    public SuspendUserTask(String oid) {
        super(oid);
    }
}
