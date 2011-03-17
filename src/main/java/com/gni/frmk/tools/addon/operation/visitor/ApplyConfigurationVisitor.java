package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.gni.frmk.tools.addon.configuration.components.AdapterListener;
import com.gni.frmk.tools.addon.configuration.components.AdapterNotification;
import com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.configuration.components.JmsAlias;
import com.gni.frmk.tools.addon.configuration.components.JmsTrigger;
import com.gni.frmk.tools.addon.configuration.components.NativeTrigger;
import com.gni.frmk.tools.addon.configuration.components.Port;
import com.gni.frmk.tools.addon.configuration.components.Scheduler;
import com.gni.frmk.tools.addon.configuration.components.Scheduler.SchedulerState.SchedulerStatus;
import com.gni.frmk.tools.addon.configuration.components.TemporaryActivableState;
import com.gni.frmk.tools.addon.invoke.ServiceException;
import com.gni.frmk.tools.addon.invoke.divers.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootNativeInvoker;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class ApplyConfigurationVisitor implements ConfigurationVisitorRaisingException {

    private final WmRootNativeInvoker rootInvoker;
    private final WmRootJmsInvoker jmsInvoker;
    private final WmArtInvoker artInvoker;

    public ApplyConfigurationVisitor(WmArtInvoker artInvoker, WmRootJmsInvoker jmsInvoker,
            WmRootNativeInvoker rootInvoker) {
        this.artInvoker = artInvoker;
        this.jmsInvoker = jmsInvoker;
        this.rootInvoker = rootInvoker;
    }

    public void visit(AdapterConnection visited) throws ConfigurationVisitorException {
        try {
            switch (visited.getState().getEnabled()) {
                case ENABLED:
                    artInvoker.enableConnection(visited.getAlias());
                    break;
                case DISABLED:
                    artInvoker.disableConnection(visited.getAlias());
                    break;
                default:
                    throw new IllegalStateException("unknown state");
            }
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(AdapterListener visited) throws ConfigurationVisitorException {
        try {
            ActivableStatus state = visited.getState().getActivable();
            EnableStatus status = visited.getState().getEnabled();
            switch (state) {
                case ACTIVE:
                    artInvoker.resumeListener(visited.getName());
                    break;
                case INACTIVE:
                    artInvoker.suspendListener(visited.getName());
                    break;
            }
            switch (status) {
                case ENABLED:
                    artInvoker.enableListener(visited.getName());
                    break;
                case DISABLED:
                    artInvoker.disableListener(visited.getName());
                    break;
            }
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(AdapterNotification visited) throws ConfigurationVisitorException {
        try {
            ActivableStatus state = visited.getState().getActivable();
            EnableStatus status = visited.getState().getEnabled();
            switch (state) {
                case ACTIVE:
                    artInvoker.resumeNotification(visited.getName());
                    break;
                case INACTIVE:
                    artInvoker.suspendNotification(visited.getName());
                    break;
            }
            switch (status) {
                case ENABLED:
                    artInvoker.enableNotification(visited.getName());
                    break;
                case DISABLED:
                    artInvoker.disableNotification(visited.getName());
                    break;
            }
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Port visited) throws ConfigurationVisitorException {
        try {
            switch (visited.getState().getEnabled()) {
                case ENABLED:
                    rootInvoker.enablePortListener(visited.getKey(), visited.getPackageName());
                    break;
                case DISABLED:
                    rootInvoker.disablePortListener(visited.getKey(), visited.getPackageName());
                    break;
                default:
                    throw new IllegalStateException("unknown state");
            }
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Scheduler visited) throws ConfigurationVisitorException {
        try {
            EnableStatus enabled = visited.getState().getEnabled();
            SchedulerStatus scheduled = visited.getState().getScheduled();
            switch (enabled) {
                case ENABLED:
                    rootInvoker.wakeupUserTask(visited.getOid());
                    break;
                case DISABLED:
                    rootInvoker.suspendUserTask(visited.getOid());
                    break;
                default:
                    throw new IllegalStateException("unknown state");
            }
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(JmsAlias visited) throws ConfigurationVisitorException {
        try {
            EnableStatus enableStatus = visited.getState().getEnabled();
            switch (enableStatus) {
                case ENABLED:
                    jmsInvoker.enableConnectionAlias(visited.getName());
                    break;
                case DISABLED:
                    jmsInvoker.disableConnectionAlias(visited.getName());
                    break;
                default:
                    throw new IllegalStateException("unknown state");
            }
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(JmsTrigger visited) throws ConfigurationVisitorException {
        try {
            EnableStatus status = visited.getState().getEnabled();
            ActivableStatus activable = visited.getState().getActivable();
            switch (status) {
                case ENABLED:
                    jmsInvoker.enableJMSTriggers(visited.getName());
                    break;
                case DISABLED:
                    jmsInvoker.disableJMSTriggers(visited.getName());
                    break;
            }
            switch (activable) {
                case ACTIVE:
                    jmsInvoker.enableJMSTriggers(visited.getName());
                    break;
                case INACTIVE:
                    jmsInvoker.suspendJmsTriggers(visited.getName());
                    break;
            }
        } catch (ServiceException e) {
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
     * @throws com.gni.frmk.tools.addon.invoke.ServiceException
     *
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
            rootInvoker.suspendTriggers(true, processingSuspended, processingPermanent, visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }

        TemporaryActivableState retrievalState = visited.getState().getRetrievalState();
        boolean retrievalSuspended = !retrievalState.getActivable().isActive();
        boolean retrievalPermanent = retrievalState.getTemporary().isPermanent();
        if (retrievalSuspended && !retrievalPermanent) {
            retrievalPermanent = true;
        }
        try {
            rootInvoker.suspendTriggers(retrievalSuspended, processingSuspended, retrievalPermanent, visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }
}