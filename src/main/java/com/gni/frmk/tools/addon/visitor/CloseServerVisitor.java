package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.command.action.wm.art.DisableConnection;
import com.gni.frmk.tools.addon.command.action.wm.art.DisableListener;
import com.gni.frmk.tools.addon.command.action.wm.art.DisableNotification;
import com.gni.frmk.tools.addon.command.action.wm.jms.DisableJmsAlias;
import com.gni.frmk.tools.addon.command.action.wm.jms.DisableJmsTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.DisablePackage;
import com.gni.frmk.tools.addon.command.action.wm.root.DisablePortListener;
import com.gni.frmk.tools.addon.command.action.wm.root.SuspendTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.SuspendUserTask;
import com.gni.frmk.tools.addon.command.api.DispatchException;
import com.gni.frmk.tools.addon.oldies.invoke.WmArtInvoker;
import com.gni.frmk.tools.addon.oldies.invoke.WmRootInvoker;
import com.gni.frmk.tools.addon.oldies.invoke.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.visitor.api.ConfigurationVisitorRaisingException;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class CloseServerVisitor implements ConfigurationVisitorRaisingException {

    private final WmRootInvoker rootInvoker;
    private final WmRootJmsInvoker jmsInvoker;
    private final WmArtInvoker artInvoker;

    public CloseServerVisitor(WmArtInvoker artInvoker, WmRootJmsInvoker jmsInvoker, WmRootInvoker rootInvoker) {
        this.artInvoker = artInvoker;
        this.jmsInvoker = jmsInvoker;
        this.rootInvoker = rootInvoker;
    }

    @Override
    public void visit(AdapterConnection visited) throws ConfigurationVisitorException {
        try {
            artInvoker.disableConnection(new DisableConnection(visited.getAlias()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(AdapterListener visited) throws ConfigurationVisitorException {
        try {
            artInvoker.disableListener(new DisableListener(visited.getName()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(AdapterNotification visited) throws ConfigurationVisitorException {
        try {
            artInvoker.disableNotification(new DisableNotification(visited.getName()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(Port visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.disablePortListener(new DisablePortListener(visited.getPackageName(), visited.getKey()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(Scheduler visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.suspendUserTask(new SuspendUserTask(visited.getOid()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(JmsAlias visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.disableJmsAlias(new DisableJmsAlias(visited.getName()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(JmsTrigger visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.disableJmsTriggers(new DisableJmsTriggers(visited.getName()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(NativeTrigger visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.suspendTriggers(SuspendTriggers.builder()
                                                       .addTriggerName(visited.getName())
                                                       .persistChange(true)
                                                       .suspendProcessing(true)
                                                       .suspendRetrieval(true)
                                                       .build());
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(IntegrationServerPackage visited) throws ComponentVisitorException {
        try {
            rootInvoker.disablePackage(new DisablePackage(visited.getPackageName()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }
}