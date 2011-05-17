package com.gni.frmk.tools.addon.operation.action.component.jms.trigger;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:22
 *
 * @author: e03229
 */
public class GetJmsTrigger extends GetComponent<JmsTrigger, StringId> {
    public GetJmsTrigger(StringId id) {
        super(id);
    }
}
