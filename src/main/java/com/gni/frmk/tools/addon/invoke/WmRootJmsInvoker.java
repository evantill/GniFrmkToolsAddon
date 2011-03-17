package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.configuration.components.JmsAlias;
import com.gni.frmk.tools.addon.configuration.components.JmsTrigger;
import com.gni.frmk.tools.addon.invoke.actions.wmjms.*;
import com.gni.frmk.tools.addon.invoke.exceptions.ActionException;
import com.gni.frmk.tools.addon.invoke.exceptions.InvokeException;
import com.gni.frmk.tools.addon.invoke.handlers.wmjms.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 13 oct. 2010
 * Time: 17:22:06
 * To change this template use File | Settings | File Templates.
 */
public class WmRootJmsInvoker extends AbstractWmHandler {

    private final String toolsPackageName;
    private final boolean jmsServicesAivable;

    public WmRootJmsInvoker(IntegrationServerUtil util, InvokeContext context, String toolsPackageName) {
        super("WmRoot", util, context);
        this.toolsPackageName = toolsPackageName;
        //check if service exist
        GetJmsAliasReportHandler jmsAliasReportHandler = new GetJmsAliasReportHandler();
        jmsServicesAivable = context.canInvoke(jmsAliasReportHandler.getService());
        //register
        addHandler(jmsAliasReportHandler);
        addHandler(new GetJmsTriggerReportHandler());
        addHandler(new DisableJmsAliasHandler());
        addHandler(new DisableJmsTriggersHandler());
        addHandler(new SuspendJmsTriggersHandler());
        addHandler(new EnableJmsAliasHandler());
        addHandler(new EnableJmsTriggersHandler());
    }

    public void suspendJmsTriggers(SuspendJmsTriggers param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void disableJmsAlias(DisableJmsAlias param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void disableJmsTriggers(DisableJmsTriggers param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void enableJmsAlias(EnableJmsAlias param) throws ActionException, InvokeException {
        invoke(param);
    }

    public void enableJmsTriggers(EnableJmsTriggers param) throws ActionException, InvokeException {
        invoke(param);
    }

    public List<JmsAlias> getJmsAliasReport() throws ActionException, InvokeException {
        return invoke(new GetJmsAliasReport()).getCollection();
    }

    public List<JmsTrigger> getJmsTriggerReport() throws InvokeException, ActionException {
        return invoke(new GetJmsTriggerReport()).getCollection();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && jmsServicesAivable;
    }
}