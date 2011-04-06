package com.gni.frmk.tools.addon.oldies.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.command.action.wm.art.*;
import com.gni.frmk.tools.addon.command.api.ActionException;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeException;
import com.gni.frmk.tools.addon.command.handler.wm.art.*;
import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.AdapterListener;
import com.gni.frmk.tools.addon.model.component.AdapterNotification;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 18:58
 *
 * @author: e03229
 */
public class WmArtInvoker extends AbstractWmHandler {

    public WmArtInvoker(IntegrationServerUtil util, InvokeContext context) {
        super("WmArt", util, context);
        addHandler(new DisableConnectionHandler());
        addHandler(new DisableListenerHandler());
        addHandler(new DisableNotificationHandler());
        addHandler(new EnableConnectionHandler());
        addHandler(new EnableListenerHandler());
        addHandler(new EnableNotificationHandler());
        addHandler(new ListAdapterConnectionsHandler());
        addHandler(new ListListenersHandler());
        addHandler(new ListNotificationsHandler());
        addHandler(new ResumeListenerHandler());
        addHandler(new ResumeNotificationHandler());
        addHandler(new RetrieveAdapterTypesListHandler());
        addHandler(new SuspendListenerHandler());
        addHandler(new SuspendNotificationHandler());
    }

    public void disableConnection(DisableConnection param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void disableListener(DisableListener param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void disableNotification(DisableNotification param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void enableConnection(EnableConnection param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void enableListener(EnableListener param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void enableNotification(EnableNotification param) throws ActionException, InvokeException {
        invoke(param);
    }

    public List<AdapterConnection> listAdaptersConnections(ListAdaptersConnections param) throws ActionException, InvokeException {
        return invoke(param).getCollection();
    }

    public List<AdapterListener> listListeners(ListListeners param) throws ActionException, InvokeException {
        return invoke(param).getCollection();
    }

    public List<AdapterNotification> listNotifications(ListNotifications param) throws ActionException, InvokeException {
        return invoke(param).getCollection();
    }

    public void resumeListener(ResumeListener param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void resumeNotification(ResumeNotification param) throws ActionException, InvokeException {
        invoke(param);
    }

    public Set<String> retrieveAdapterTypesList(RetrieveAdapterTypesList param) throws ActionException, InvokeException {
        return invoke(param).getCollection();
    }

    public void suspendListener(SuspendListener param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void suspendNotification(SuspendNotification param) throws ActionException, InvokeException {
        invoke(param);
    }

}