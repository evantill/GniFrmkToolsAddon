package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.gni.frmk.tools.addon.configuration.components.AdapterListener;
import com.gni.frmk.tools.addon.configuration.components.AdapterNotification;
import com.gni.frmk.tools.addon.invoke.AbstractWmInvoker;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionException;
import com.gni.frmk.tools.addon.invoke.InvokeContext;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 18:58
 *
 * @author: e03229
 */
public class WmArtInvoker extends AbstractWmInvoker {

    public WmArtInvoker(IntegrationServerUtil util, InvokeContext context) {
        super("WmArt", util, context);
        addHandler(new DisableConnectionInvoker());
        addHandler(new DisableListenerInvoker());
        addHandler(new DisableNotificationInvoker());
        addHandler(new EnableConnectionInvoker());
        addHandler(new EnableListenerInvoker());
        addHandler(new EnableNotificationInvoker());
        addHandler(new ListAdapterConnectionsInvoker());
        addHandler(new ListListenersInvoker());
        addHandler(new ListNotificationsInvoker());
        addHandler(new ResumeListenerInvoker());
        addHandler(new ResumeNotificationInvoker());
        addHandler(new RetrieveAdapterTypesListInvoker());
        addHandler(new SuspendListenerInvoker());
        addHandler(new SuspendNotificationInvoker());
    }

    public void disableConnection(DisableConnection param) throws ActionException {
        invoke(param);
    }

    public void disableListener(DisableListener param) throws ActionException {
        invoke(param);
    }

    public void disableNotification(DisableNotification param) throws ActionException {
        invoke(param);
    }

    public void enableConnection(EnableConnection param) throws ActionException {
        invoke(param);
    }

    public void enableListener(EnableListener param) throws ActionException {
        invoke(param);
    }

    public void enableNotification(EnableNotification param) throws ActionException {
        invoke(param);
    }

    public List<AdapterConnection> listAdaptersConnections(ListAdaptersConnections param) throws ActionException {
        return invoke(param).getCollection();
    }

    public List<AdapterListener> listListeners(ListListeners param) throws ActionException {
        return invoke(param).getCollection();
    }

    public List<AdapterNotification> listNotifications(ListNotifications param) throws ActionException {
        return invoke(param).getCollection();
    }

    public void resumeListener(ResumeListener param) throws ActionException {
        invoke(param);
    }

    public void resumeNotification(ResumeNotification param) throws ActionException {
        invoke(param);
    }

    public Set<String> retrieveAdapterTypesList(RetrieveAdapterTypesList param) throws ActionException {
        return invoke(param).getCollection();
    }

    public void suspendListener(SuspendListener param) throws ActionException {
        invoke(param);
    }

    public void suspendNotification(SuspendNotification param) throws ActionException {
        invoke(param);
    }

}
