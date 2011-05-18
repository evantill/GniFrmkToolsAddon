package com.gni.frmk.tools.addon.operation.action.component.art.notifications;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:40
 *
 * @author: e03229
 */
public class GetAdapterNotification extends GetComponent<AdapterNotification, AdapterId> {
    public GetAdapterNotification(AdapterId id) {
        super(id);
    }
}
