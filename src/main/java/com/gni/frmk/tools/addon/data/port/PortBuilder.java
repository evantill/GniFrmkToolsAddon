package com.gni.frmk.tools.addon.data.port;

import com.gni.frmk.tools.addon.data.component.ActivableComponentState;
import com.gni.frmk.tools.addon.data.component.ComponentBuilder;
import com.gni.frmk.tools.addon.data.component.ComponentPackageInfo;
import com.gni.frmk.tools.addon.data.component.ComponentState;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import static com.gni.frmk.tools.addon.data.component.ActivableComponentState.ActiveStatus.ACTIVE;
import static com.gni.frmk.tools.addon.data.component.ActivableComponentState.ActiveStatus.SUSPENDED;
import static com.gni.frmk.tools.addon.data.component.ComponentState.EnableStatus.DISABLED;
import static com.gni.frmk.tools.addon.data.component.ComponentState.EnableStatus.ENABLED;
import static com.gni.frmk.tools.addon.data.component.ComponentType.PORT;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 18:17:16
 * To change this template use File | Settings | File Templates.
 */
public class PortBuilder extends ComponentBuilder<Port, ComponentPackageInfo, ActivableComponentState> {

    public static final class CustomXmlAdapter extends XmlAdapter<ComponentBuilder<Port, ComponentPackageInfo, ActivableComponentState>, Port> {
        @Override
        public Port unmarshal(ComponentBuilder<Port, ComponentPackageInfo, ActivableComponentState> v) throws Exception {
            return v.build();
        }

        @Override
        public ComponentBuilder<Port, ComponentPackageInfo, ActivableComponentState> marshal(Port v) throws Exception {
            return new PortBuilder().define(v);
        }
    }

    String key;
    String packageName;
    ActivableComponentState.EnableStatus enableStatus;
    ActivableComponentState.ActiveStatus activeStatus;

    public PortBuilder define(String key, String packageName, ComponentState.EnableStatus enableStatus, ActivableComponentState.ActiveStatus activeStatus) {
        this.enableStatus = enableStatus;
        this.activeStatus = activeStatus;
        this.key = key;
        this.packageName = packageName;
        return this;
    }

    /**
     * use pipeline data from service wm.server.ports:listListeners
     *
     * @param doc the datas of one port
     * @return builder for chain calling
     */
    public PortBuilder define(IData doc) {
        IDataCursor curDoc = doc.getCursor();
        try {
            key = checkNotNull(IDataUtil.getString(curDoc, "key"));
            packageName = checkNotNull(IDataUtil.getString(curDoc, "pkg"));
            if (IDataUtil.getBoolean(curDoc, "enabled")) {
                enableStatus = ENABLED;
            } else {
                enableStatus = DISABLED;
            }
            checkNotNull(enableStatus);
            if (IDataUtil.getBoolean(curDoc, "suspended")) {
                activeStatus = SUSPENDED;
            } else {
                activeStatus = ACTIVE;
            }
            checkNotNull(activeStatus);
        } finally {
            curDoc.destroy();
        }
        return this;
    }

    public Port build() {
        this.defineType(PORT)
                .defineId(key)
                .defineState(new ActivableComponentState(enableStatus, activeStatus))
                .defineInfo(new ComponentPackageInfo(packageName));
        return new Port(this);
    }
}
