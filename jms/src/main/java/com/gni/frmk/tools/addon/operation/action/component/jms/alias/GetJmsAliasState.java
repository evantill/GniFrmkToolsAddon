package com.gni.frmk.tools.addon.operation.action.component.jms.alias;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.model.component.ConnectableState;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 15:57
 *
 * @author: e03229
 */
public class GetJmsAliasState extends GetComponentState<ConnectableState, StringId> {

    public GetJmsAliasState(StringId id) {
        super(id);
    }
}

