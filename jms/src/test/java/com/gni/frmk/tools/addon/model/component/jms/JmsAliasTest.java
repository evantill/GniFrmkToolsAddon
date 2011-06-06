package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.AbstractComponentTest;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/06/11
 * Time: 10:13
 *
 * @author: e03229
 */
public class JmsAliasTest extends AbstractComponentTest<JmsAlias> {

    public JmsAliasTest() {
        super(JmsAlias.class);
    }

    @Override
    protected JmsAlias buildComponent() {
        return JmsAlias.builder()
                       .id(StringId.build("jms alias name 1"))
                       .state(ConnectableState.build(EnableStatus.ENABLED, ConnectableStatus.CONNECTED))
                       .detail(JmsAliasDetail.build("description of jms alias 1"))
                       .validate()
                       .build();
    }
}
