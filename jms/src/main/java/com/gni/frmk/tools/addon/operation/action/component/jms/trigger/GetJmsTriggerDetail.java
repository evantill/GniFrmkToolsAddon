package com.gni.frmk.tools.addon.operation.action.component.jms.trigger;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger.JmsTriggerDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 15:56
 *
 * @author: e03229
 */
public class GetJmsTriggerDetail extends GetComponentDetail<JmsTriggerDetail,StringId> {
    public GetJmsTriggerDetail(StringId id) {
        super(id);
    }
}
