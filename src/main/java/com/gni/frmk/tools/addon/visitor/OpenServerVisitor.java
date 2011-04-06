package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.command.action.wm.art.EnableConnection;
import com.gni.frmk.tools.addon.command.action.wm.art.EnableListener;
import com.gni.frmk.tools.addon.command.action.wm.art.EnableNotification;
import com.gni.frmk.tools.addon.command.action.wm.jms.EnableJmsAlias;
import com.gni.frmk.tools.addon.command.action.wm.jms.EnableJmsTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.EnablePackage;
import com.gni.frmk.tools.addon.command.action.wm.root.EnablePortListener;
import com.gni.frmk.tools.addon.command.action.wm.root.SuspendTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.WakeUpUserTask;
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