package com.gni.frmk.tools.addon.data.trigger;

import com.gni.frmk.tools.addon.data.component.ActivableComponentState;
import com.gni.frmk.tools.addon.data.component.ComponentBuilder;
import com.gni.frmk.tools.addon.data.component.ComponentState;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import static com.gni.frmk.tools.addon.data.component.ComponentType.JMS_ALIAS;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 4 oct. 2010
 * Time: 14:23:59
 * To change this template use File | Settings | File Templates.
 */
public class JmsAliasBuilder extends ComponentBuilder<JmsAlias, JmsAliasInfo, ActivableComponentState> {

    public static final class CustomXmlAdapter extends XmlAdapter<ComponentBuilder<JmsAlias, JmsAliasInfo, ActivableComponentState>, JmsAlias> {
        @Override
        public JmsAlias unmarshal(ComponentBuilder<JmsAlias, JmsAliasInfo, ActivableComponentState> v) throws Exception {
            return v.build();
        }

        @Override
        public ComponentBuilder<JmsAlias, JmsAliasInfo, ActivableComponentState> marshal(JmsAlias v) throws Exception {
            return new JmsAliasBuilder().define(v);
        }
    }

    ActivableComponentState.EnableStatus enableStatus;
    ActivableComponentState.ActiveStatus activeStatus;
    String aliasName;
    String description;

    public JmsAliasBuilder define(String aliasName, String description, ComponentState.EnableStatus enableStatus, ActivableComponentState.ActiveStatus activeStatus) {
        this.enableStatus = checkNotNull(enableStatus);
        this.activeStatus = checkNotNull(activeStatus);
        this.aliasName = checkNotNull(aliasName);
        this.description = description;
        return this;
    }

    /**
     * use pipeline data from service wm.server.jms:getConnectionAliasReport.
     *
     * @param doc datas of an alias
     * @return builder for chain calling
     */
    public JmsAliasBuilder define(IData doc) {
        IDataCursor curDoc = doc.getCursor();
        try {
            aliasName = IDataUtil.getString(curDoc, "aliasName");
            description = IDataUtil.getString(curDoc, "description");
            if (IDataUtil.getBoolean(curDoc, "enabled", false)) {
                enableStatus = ComponentState.EnableStatus.ENABLED;
            } else {
                enableStatus = ComponentState.EnableStatus.DISABLED;
            }
            if (IDataUtil.getBoolean(curDoc, "connected", false)) {
                activeStatus = ActivableComponentState.ActiveStatus.ACTIVE;
            } else {
                activeStatus = ActivableComponentState.ActiveStatus.SUSPENDED;
            }
        } finally {
            curDoc.destroy();
        }
        return this;
    }

    public JmsAlias build() {
        this.defineType(JMS_ALIAS)
                .defineId(aliasName)
                .defineState(new ActivableComponentState(enableStatus, activeStatus))
                .defineInfo(new JmsAliasInfo(description));
        return new JmsAlias(this);
    }

}
