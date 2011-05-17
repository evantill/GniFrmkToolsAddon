package com.gni.frmk.tools.addon.operation.action.component.jms.alias;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetJmsAlias extends GetComponent<JmsAlias, StringId> {

    public GetJmsAlias(StringId id) {
        super(id);
    }

}
