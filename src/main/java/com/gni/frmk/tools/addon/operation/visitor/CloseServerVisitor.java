package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.adapter.AdapterConnection;
import com.gni.frmk.tools.addon.data.adapter.AdapterListener;
import com.gni.frmk.tools.addon.data.adapter.AdapterNotification;
import com.gni.frmk.tools.addon.data.port.Port;
import com.gni.frmk.tools.addon.data.scheduler.Scheduler;
import com.gni.frmk.tools.addon.data.trigger.JmsAlias;
import com.gni.frmk.tools.addon.data.trigger.JmsTrigger;
import com.gni.frmk.tools.addon.data.trigger.NativeTrigger;
import com.gni.frmk.tools.addon.invoke.ServiceException;
import com.gni.frmk.tools.addon.invoke.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootNativeInvoker;

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

    public void visit(AdapterConnection visited) throws ConfigurationVisitorException {
        try {
            artInvoker.disableConnection(visited.getInfos().getAlias());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(AdapterListener visited) throws ConfigurationVisitorException {
        try {
            artInvoker.disableListener(visited.getInfos().getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(AdapterNotification visited) throws ConfigurationVisitorException {
        try {
            artInvoker.disableNotification(visited.getInfos().getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Port visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.disablePortListener(visited.getId().getKey(), visited.getInfos().getPackageName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Scheduler visited) throws ConfigurationVisitorException {
        //TODO  a corriger
//        try {
//            rootInvoker.suspendUserTask(visited.getOid());
//        } catch (ServiceException e) {
//            throw new ConfigurationVisitorException(visited, e);
//        }
    }

    public void visit(JmsAlias visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.disableConnectionAlias(visited.getId().getKey());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(JmsTrigger visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.disableJMSTriggers(visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(NativeTrigger visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.suspendTriggers(true, true, true, visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

}
