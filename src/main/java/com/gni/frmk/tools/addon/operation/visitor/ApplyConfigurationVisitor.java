package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.component.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.configuration.component.*;
import com.gni.frmk.tools.addon.configuration.component.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.configuration.component.SchedulerState.SchedulerStatus;
import com.gni.frmk.tools.addon.dispatcher.DispatchException;
import com.gni.frmk.tools.addon.invoke.action.wmjms.DisableJmsTriggers;
import com.gni.frmk.tools.addon.invoke.action.wmjms.*;
import com.gni.frmk.tools.addon.invoke.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.action.wmjms.DisableJmsAlias;
import com.gni.frmk.tools.addon.invoke.action.wmart.*;
import com.gni.frmk.tools.addon.invoke.action.wmjms.EnableJmsTriggers;
import com.gni.frmk.tools.addon.invoke.action.wmjms.SuspendJmsTriggers;
import com.gni.frmk.tools.addon.invoke.action.wmroot.*;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class ApplyConfigurationVisitor implements ConfigurationVisitorRaisingException {

    private final WmRootInvoker rootInvoker;
    private final WmRootJmsInvoker jmsInvoker;
    private final WmArtInvoker artInvoker;

    public ApplyConfigurationVisitor(WmArtInvoker artInvoker, WmRootJmsInvoker jmsInvoker,
            WmRootInvoker rootInvoker) {
        this.artInvoker = artInvoker;
        this.jmsInvoker = jmsInvoker;
        this.rootInvoker = rootInvoker;
    }

    public void visit(AdapterConnection visited) throws ConfigurationVisitorException {
        try {
            switch (visited.getState().getEnabled()) {
                case ENABLED:
                    artInvoker.enableConnection(new EnableConnection(visited.getAlias()));
                    break;
                case DISABLED:
                    artInvoker.disableConnection(new DisableConnection(visited.getAlias()));
                    break;
                default:
                    throw new IllegalStateException("unknown state");
            }
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(AdapterListener visited) throws ConfigurationVisitorException {
        try {
            ActivableStatus state = visited.getState().getActivable();
            EnableStatus status = visited.getState().getEnabled();
            switch (state) {
                case ACTIVE:
                    artInvoker.resumeListener(new ResumeListener(visited.getName()));
                    break;
                case INACTIVE:
                    artInvoker.suspendListener(new SuspendListener(visited.getName()));
                    break;
            }
            switch (status) {
                case ENABLED:
                    artInvoker.enableListener(new EnableListener(visited.getName()));
                    break;
                case DISABLED:
                    artInvoker.disableListener(new DisableListener(visited.getName()));
                    break;
            }
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(AdapterNotification visited) throws ConfigurationVisitorException {
        try {
            ActivableStatus state = visited.getState().getActivable();
            EnableStatus status = visited.getState().getEnabled();
            switch (state) {
                case ACTIVE:
                    artInvoker.resumeNotification(new ResumeNotification(visited.getName()));
                    break;
                case INACTIVE:
                    artInvoker.suspendNotification(new SuspendNotification(visited.getName()));
                    break;
            }
            switch (status) {
                case ENABLED:
                    artInvoker.enableNotification(new EnableNotification(visited.getName()));
                    break;
                case DISABLED:
                    artInvoker.disableNotification(new DisableNotification(visited.getName()));
                    break;
            }
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Port visited) throws ConfigurationVisitorException {
        try {
            switch (visited.getState().getEnabled()) {
                case ENABLED:
                    rootInvoker.enablePortListener(new EnablePortListener(visited.getPackageName(), visited.getKey()));
                    break;
                case DISABLED:
                    rootInvoker.disablePortListener(new DisablePortListener(visited.getPackageName(), visited.getKey()));
                    break;
                default:
                    throw new IllegalStateException("unknown state");
            }
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Scheduler visited) throws ConfigurationVisitorException {
        try {
            EnableStatus enabled = visited.getState().getEnabled();
            //TODO utiliser ou supprimer scheduled state
            SchedulerStatus scheduled = visited.getState().getScheduled();
            switch (enabled) {
                case ENABLED:
                    rootInvoker.wakeUpUserTask(new WakeUpUserTask(visited.getOid()));
                    break;
                case DISABLED:
                    rootInvoker.suspendUserTask(new SuspendUserTask(visited.getOid()));
                    break;
                default:
                    throw new IllegalStateException("unknown state");
            }
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    @Override
    public void visit(IntegrationServerPackage visited) throws ConfigurationVisitorException {
        try {
            EnableStatus enabled = visited.getState().getEnabled();
            switch (enabled) {
                case ENABLED:
                    rootInvoker.enablePackage(new EnablePackage(visited.getPackageName()));
                    break;
                case DISABLED:
                    rootInvoker.disablePackage(new DisablePackage(visited.getPackageName()));
                    break;
                default:
                    throw new IllegalStateException(String.format("unknown enable state %s", enabled));
            }
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(JmsAlias visited) throws ConfigurationVisitorException {
        try {
            EnableStatus enableStatus = visited.getState().getEnabled();
            switch (enableStatus) {
                case ENABLED:
                    jmsInvoker.enableJmsAlias(new EnableJmsAlias(visited.getName()));
                    break;
                case DISABLED:
                    jmsInvoker.disableJmsAlias(new DisableJmsAlias(visited.getName()));
                    break;
                default:
                    throw new IllegalStateException("unknown state");
            }
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(JmsTrigger visited) throws ConfigurationVisitorException {
        try {
            EnableStatus status = visited.getState().getEnabled();
            ActivableStatus activable = visited.getState().getActivable();
            switch (status) {
                case ENABLED:
                    jmsInvoker.enableJmsTriggers(new EnableJmsTriggers(visited.getName()));
                    break;
                case DISABLED:
                    jmsInvoker.disableJmsTriggers(new DisableJmsTriggers(visited.getName()));
                    break;
            }
            switch (activable) {
                case ACTIVE:
                    jmsInvoker.enableJmsTriggers(new EnableJmsTriggers(visited.getName()));
                    break;
                case INACTIVE:
                    jmsInvoker.suspendJmsTriggers(new SuspendJmsTriggers(visited.getName()));
                    break;
            }
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    /**
     * Le changement de statut est le suivant :
     * <code>
     * <ul>
     * <li>Enable/Permanent -> Enable/Permanent</li>
     * <li>Disable/Permanent -> Disable/Permanent</li>
     * <li>Enable/Temporary -> Enable/Temporary</li>
     * <li>Disable/Temporary -> Disable/Permanent</li>
     * </ul>
     * </code>
     *
     * @param visited
     * @throws ConfigurationVisitorException
     */
    public void visit(NativeTrigger visited) throws ConfigurationVisitorException {
        //TODO verifier pourquoi enables est non utilisé
        //visited.getState().getEnabled()
        TemporaryActivableState processingState = visited.getState().getProcessingState();
        boolean processingSuspended = !processingState.getActivable().isActive();
        boolean processingPermanent = processingState.getTemporary().isPermanent();
        if (processingSuspended && !processingPermanent) {
            processingPermanent = true;
        }
        try {
            SuspendTriggers action = SuspendTriggers.builder().addTriggerName(visited.getName())
                                                    .suspendRetrieval(true)
                                                    .suspendProcessing(processingSuspended)
                                                    .persistChange(processingPermanent).build();
            rootInvoker.suspendTriggers(action);
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }

        TemporaryActivableState retrievalState = visited.getState().getRetrievalState();
        boolean retrievalSuspended = !retrievalState.getActivable().isActive();
        boolean retrievalPermanent = retrievalState.getTemporary().isPermanent();
        if (retrievalSuspended && !retrievalPermanent) {
            retrievalPermanent = true;
        }
        try {
            SuspendTriggers action = SuspendTriggers.builder().addTriggerName(visited.getName())
                                                    .suspendRetrieval(retrievalSuspended)
                                                    .suspendProcessing(processingSuspended)
                                                    .persistChange(retrievalPermanent).build();
            rootInvoker.suspendTriggers(action);
        } catch (DispatchException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }
}