package com.gni.frmk.tools.addon.operation.action.component.jms.alias;

import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 13:27
 *
 * @author: e03229
 */
public class ChangeJmsAliasState extends ChangeComponentState<StringId, ConnectableState> {

    public ChangeJmsAliasState(ConnectableState newComponentState, JmsAlias component) {
        super(newComponentState, component);
    }
}
