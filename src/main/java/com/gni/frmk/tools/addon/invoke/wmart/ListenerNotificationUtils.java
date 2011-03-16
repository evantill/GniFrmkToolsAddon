package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.configuration.components.ActivableState;
import com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus;

import static com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus.ACTIVE;
import static com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus.INACTIVE;
import static com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus.DISABLED;
import static com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus.ENABLED;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 18:38
 *
 * @author: e03229
 */
class ListenerNotificationUtils {
    public static final ActivableState defineState(String notificationEnabled) {
        EnableStatus enableStatus;
        ActivableStatus activableStatus;
        if ("yes".equals(notificationEnabled)) {
            enableStatus = ENABLED;
            activableStatus = ACTIVE;
        } else if ("no".equals(notificationEnabled)) {
            enableStatus = DISABLED;
            activableStatus = ACTIVE;
        } else if ("suspended".equals(notificationEnabled)) {
            enableStatus = ENABLED;
            activableStatus = INACTIVE;
        } else if ("unsched".equals(notificationEnabled)) {
            enableStatus = DISABLED;
            activableStatus = INACTIVE;
        } else {
            throw new IllegalStateException(String.format("unknown notification state %s", notificationEnabled));
        }
        return new ActivableState(checkNotNull(enableStatus), checkNotNull(activableStatus));
    }

}
