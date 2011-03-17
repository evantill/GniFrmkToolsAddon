package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.components.*;
import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitorException;
import com.gni.frmk.tools.addon.invoke.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.actions.wmart.EnableConnection;
import com.gni.frmk.tools.addon.invoke.actions.wmart.EnableListener;
import com.gni.frmk.tools.addon.invoke.actions.wmart.EnableNotification;
import com.gni.frmk.tools.addon.invoke.actions.wmjms.EnableJmsAlias;
import com.gni.frmk.tools.addon.invoke.actions.wmjms.EnableJmsTriggers;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.EnablePackage;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.EnablePortListener;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.SuspendTriggers;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.WakeUpUserTask;
import com.gni.frmk.tools.addon.invoke.exceptions.DispatchException;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class OpenServerVisitor implements ConfigurationVisitorRaisingException {

    private final WmRootInvoker rootInvoker;
    private final WmRootJmsInvoker jmsInvoker;
    private final WmArtInvoker artInvoker;

    public OpenServerVisitor(WmArtInvoker artInvoker, WmRootJmsInvoker jmsInvoker, WmRootInvoker rootInvoker) {
        this.artInvoker = artInvoker;
        this.jmsInvoker = jmsInvoker;
        this.rootInvoker = rootInvoker;
    }

    @Override
    public void visit(AdapterConnection visited) throws ConfigurationVisitorException {
        try {
            artInvoker.enableConnection(new EnableConnection(visited.getAlias()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(AdapterListener visited) throws ConfigurationVisitorException {
        try {
            artInvoker.enableListener(new EnableListener(visited.getName()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(AdapterNotification visited) throws ConfigurationVisitorException {
        try {
            artInvoker.enableNotification(new EnableNotification(visited.getName()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(Port visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.enablePortListener(new EnablePortListener(visited.getPackageName(), visited.getKey()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(Scheduler visited) throws ConfigurationVisitorException {
        try {
            rootInvoker.wakeUpUserTask(new WakeUpUserTask(visited.getOid()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(JmsAlias visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.enableJmsAlias(new EnableJmsAlias(visited.getName()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(JmsTrigger visited) throws ConfigurationVisitorException {
        try {
            jmsInvoker.enableJmsTriggers(new EnableJmsTriggers(visited.getName()));
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
                                                       .suspendProcessing(false)
                                                       .suspendRetrieval(false)
                                                       .build());
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(IntegrationServerPackage visited) throws ComponentVisitorException {
        try {
            rootInvoker.enablePackage(new EnablePackage(visited.getPackageName()));
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }
}