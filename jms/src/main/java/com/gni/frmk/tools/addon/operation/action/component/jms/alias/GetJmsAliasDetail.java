package com.gni.frmk.tools.addon.operation.action.component.jms.alias;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 15:57
 *
 * @author: e03229
 */
public class GetJmsAliasDetail extends GetComponentDetail<JmsAliasDetail,StringId> {

    public GetJmsAliasDetail(StringId id) {
        super(id);
    }
}
