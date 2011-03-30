package com.gni.frmk.tools.addon.invoke.action.wmroot;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.dispatcher.StringAction;

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
