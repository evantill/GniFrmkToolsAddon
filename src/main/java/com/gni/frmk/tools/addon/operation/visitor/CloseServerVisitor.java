package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.gni.frmk.tools.addon.configuration.components.AdapterListener;
import com.gni.frmk.tools.addon.configuration.components.AdapterNotification;
import com.gni.frmk.tools.addon.configuration.components.JmsAlias;
import com.gni.frmk.tools.addon.configuration.components.JmsTrigger;
import com.gni.frmk.tools.addon.configuration.components.NativeTrigger;
import com.gni.frmk.tools.addon.configuration.components.Port;
import com.gni.frmk.tools.addon.configuration.components.Scheduler;
import com.gni.frmk.tools.addon.invoke.ServiceException;
import com.gni.frmk.tools.addon.invoke.divers.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootNativeInvoker;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class CloseServerVisitor implements ConfigurationVisitorRaisingException {

    private final WmRootNativeInvoker rootInvoker;
    private final WmRootJmsInvoker jmsInvoker;
    private final WmArtInvoker artInvoker;

    public CloseServerVisitor(WmArtInvoker artInvoker, WmRootJmsInvoker jmsInvoker, WmRootNativeInvoker rootInvoker) {
        this.artInvoker = artInvoker;
        this.jmsInvoker = jmsInvoker;
        this.rootInvoker = rootInvoker;
    }

    @Override
    public void visit(AdapterConnection visited) throws ConfigurationVisitorException {
        try {
            artInvoker.disableConnection(visited.getAlias());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(AdapterListener visited) throws ConfigurationVisitorException {
        try {
            artInvoker.disableListener(visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(AdapterNotification visited) throws ConfigurationVisitorException {
        try {
            artInvoker.disableNotification(visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(Port visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.disablePortListener(visited.getKey(), visited.getPackageName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(Scheduler visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.suspendUserTask(visited.getOid());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(JmsAlias visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.disableConnectionAlias(visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(JmsTrigger visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.disableJMSTriggers(visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(NativeTrigger visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.suspendTriggers(true, true, true, visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

}
