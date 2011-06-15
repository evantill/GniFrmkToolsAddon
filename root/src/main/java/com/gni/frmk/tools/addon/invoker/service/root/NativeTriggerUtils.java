package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInfo.NativeTriggerInfoState;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 19:37
 *
 * @author: e03229
 */
class NativeTriggerUtils {

    public static NativeTriggerInfoState parseState(IData doc) {
        if (doc == null) {
            //TODO add log
            return NativeTriggerInfoState.PERMANENT_INACTIVE;
        }
        IDataCursor curDoc = doc.getCursor();
        try {
            String stateValue = IDataUtil.getString(curDoc, "state");
            boolean permanent = true;
            {
                int dashPos = stateValue.indexOf('-');
                if (dashPos > 0) {
                    permanent = false;
                    stateValue = stateValue.substring(0, dashPos);
                }
            }
            boolean active = decodeBoolean(stateValue.toUpperCase(), "ACTIVE", "SUSPENDED");
            return NativeTriggerInfoState.decode(permanent, active);
        } finally {
            curDoc.destroy();
        }
    }

    public static boolean decodeBoolean(String text, String trueValue, String falseValue) {
        if (trueValue.equals(text)) {
            return true;
        } else if (falseValue.equals(text)) {
            return false;
        } else {
            throw new IllegalStateException(String.format("boolean decode error : %s is neither %s nor %s", text, trueValue, falseValue));
        }
    }

}
