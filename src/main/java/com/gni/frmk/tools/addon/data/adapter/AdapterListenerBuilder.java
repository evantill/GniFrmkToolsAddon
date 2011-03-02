package com.gni.frmk.tools.addon.data.adapter;

import com.gni.frmk.tools.addon.data.component.ActivableComponentState;
import com.gni.frmk.tools.addon.data.component.ComponentBuilder;
import com.gni.frmk.tools.addon.data.component.ComponentState;
import com.wm.data.IData;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import static com.gni.frmk.tools.addon.data.component.ComponentType.ADAPTER_LISTENER;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class AdapterListenerBuilder extends AdapterBuilder<AdapterListener, AdapterListenerInfo, ActivableComponentState> {

    public static final class CustomXmlAdapter extends XmlAdapter<ComponentBuilder<AdapterListener, AdapterListenerInfo, ActivableComponentState>, AdapterListener> {
        @Override
        public AdapterListener unmarshal(ComponentBuilder<AdapterListener, AdapterListenerInfo, ActivableComponentState> v) throws Exception {
            return v.build();
        }

        @Override
        public ComponentBuilder<AdapterListener, AdapterListenerInfo, ActivableComponentState> marshal(AdapterListener v) throws Exception {
            return new AdapterListenerBuilder(v.getInfos().getAdapterType()).define(v);
        }
    }

    private AdapterNotificationBuilder delegate;

    public AdapterListenerBuilder(String adapterType) {
        super(adapterType);
        delegate = new AdapterNotificationBuilder(adapterType);
    }

    /**
     * use pipeline data from service pub.art.notification:listAdapterPollingNotifications
     *
     * @param doc the datas of one notification
     * @return builder for chain calling
     */
    public ComponentBuilder<AdapterListener, AdapterListenerInfo, ActivableComponentState> define(IData doc) {
        delegate.define(doc);
        return this;
    }

    public ComponentBuilder<AdapterListener, AdapterListenerInfo, ActivableComponentState> define(String name,
                                                                                                  String packageName,
                                                                                                  ComponentState.EnableStatus enableStatus,
                                                                                                  ActivableComponentState.ActiveStatus activeStatus) {
        delegate.define(name, packageName, enableStatus, activeStatus);
        return this;
    }

    @Override
    public AdapterListener build() {
        this.defineType(ADAPTER_LISTENER)
                .defineId(delegate.name)
                .defineState(new ActivableComponentState(delegate.enableStatus, delegate.activeStatus))
                .defineInfo(new AdapterListenerInfo(adapterType, delegate.packageName, delegate.name));
        return new AdapterListener(this);
    }
}
