package com.gni.frmk.tools.addon.operation.handler.component.art;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;

import static com.gni.frmk.tools.addon.model.component.ActivableStatus.ACTIVE;
import static com.gni.frmk.tools.addon.model.component.ActivableStatus.INACTIVE;
import static com.gni.frmk.tools.addon.model.component.EnableStatus.DISABLED;
import static com.gni.frmk.tools.addon.model.component.EnableStatus.ENABLED;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 18:38
 *
 * @author: e03229
 */
public class ListenerNotificationUtils {
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
