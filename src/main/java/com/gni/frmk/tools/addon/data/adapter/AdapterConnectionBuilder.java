package com.gni.frmk.tools.addon.data.adapter;

import com.gni.frmk.tools.addon.data.component.ComponentBuilder;
import com.gni.frmk.tools.addon.data.component.ComponentState;
import com.gni.frmk.tools.addon.data.component.ComponentState.EnableStatus;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import static com.gni.frmk.tools.addon.data.component.ComponentState.EnableStatus.valueOf;
import static com.gni.frmk.tools.addon.data.component.ComponentType.ADAPTER_CONNECTION;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 18:51:14
 * To change this template use File | Settings | File Templates.
 */
public class AdapterConnectionBuilder extends ComponentBuilder<AdapterConnection, AdapterConnectionInfo, ComponentState> {

    public static final class CustomXmlAdapter extends XmlAdapter<ComponentBuilder<AdapterConnection, AdapterConnectionInfo, ComponentState>, AdapterConnection> {
        @Override
        public AdapterConnection unmarshal(ComponentBuilder<AdapterConnection, AdapterConnectionInfo, ComponentState> v) throws Exception {
            return v.build();
        }

        @Override
        public ComponentBuilder<AdapterConnection, AdapterConnectionInfo, ComponentState> marshal(AdapterConnection v) throws Exception {
            return new AdapterConnectionBuilder(v.getInfos().getAdapterType()).define(v);
        }
    }

    private final String adapterType;

    public AdapterConnectionBuilder(String adapterType) {
        this.adapterType = adapterType;
    }

    @Override
    public AdapterConnection build() {
        return new AdapterConnection(this);
    }

    /**
     * use pipeline data from service pub.art.connection:listAdapterConnections
     *
     * @param doc the datas of one port
     * @return builder for chain calling
     */
    public ComponentBuilder<AdapterConnection, AdapterConnectionInfo, ComponentState> define(IData doc) {
        IDataCursor curDoc = doc.getCursor();
        try {
            defineType(ADAPTER_CONNECTION);
            String alias = checkNotNull(IDataUtil.getString(curDoc, "connectionAlias"));
            defineId(alias);
            String connectionStateName = checkNotNull(IDataUtil.getString(curDoc, "connectionState").toUpperCase());
            defineState(new ComponentState(valueOf(connectionStateName)));
            String packageName = checkNotNull(IDataUtil.getString(curDoc, "packageName"));
            defineInfo(new AdapterConnectionInfo(checkNotNull(adapterType), packageName, alias));
            return this;
        } finally {
            curDoc.destroy();
        }
    }

    public ComponentBuilder<AdapterConnection, AdapterConnectionInfo, ComponentState> define(String alias, String packageName, EnableStatus connectionState) {
        return defineType(ADAPTER_CONNECTION)
                .defineId(alias)
                .defineState(new ComponentState(connectionState))
                .defineInfo(new AdapterConnectionInfo(checkNotNull(adapterType), packageName, alias));
    }


}
