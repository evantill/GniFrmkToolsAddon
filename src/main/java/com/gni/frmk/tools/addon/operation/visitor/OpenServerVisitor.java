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
import com.gni.frmk.tools.addon.invoke.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootNativeInvoker;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class OpenServerVisitor implements ConfigurationVisitorRaisingException {
    private final WmRootNativeInvoker rootInvoker;
    private final WmRootJmsInvoker jmsInvoker;
    private final WmArtInvoker artInvoker;

    public OpenServerVisitor(WmArtInvoker artInvoker, WmRootJmsInvoker jmsInvoker, WmRootNativeInvoker rootInvoker) {
        this.artInvoker = artInvoker;
        this.jmsInvoker = jmsInvoker;
        this.rootInvoker = rootInvoker;
    }

    public void visit(AdapterConnection visited) throws ConfigurationVisitorException {
        try {
            artInvoker.enableConnection(visited.getAlias());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(AdapterListener visited) throws ConfigurationVisitorException {
        try {
            artInvoker.enableListener(visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(AdapterNotification visited) throws ConfigurationVisitorException {
        try {
            artInvoker.enableNotification(visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Port visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.enablePortListener(visited.getKey(), visited.getPackageName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Scheduler visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.wakeupUserTask(visited.getOid());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(JmsAlias visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.enableConnectionAlias(visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(JmsTrigger visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.enableJMSTriggers(visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(NativeTrigger visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.suspendTriggers(false, false, true, visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

}
