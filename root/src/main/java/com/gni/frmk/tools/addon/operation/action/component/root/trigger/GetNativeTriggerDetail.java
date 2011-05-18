package com.gni.frmk.tools.addon.operation.action.component.root.trigger;

import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:36
 *
 * @author: e03229
 */
public class GetNativeTriggerDetail
        extends GetComponentDetail<NoDetail, StringId> {
    public GetNativeTriggerDetail(StringId id) {
        super(id);
    }
}
