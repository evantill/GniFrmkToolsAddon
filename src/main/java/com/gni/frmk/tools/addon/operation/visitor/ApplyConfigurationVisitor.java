package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.adapter.AdapterConnection;
import com.gni.frmk.tools.addon.data.adapter.AdapterListener;
import com.gni.frmk.tools.addon.data.adapter.AdapterNotification;
import com.gni.frmk.tools.addon.data.component.ActivableComponentState;
import com.gni.frmk.tools.addon.data.component.ComponentState;
import com.gni.frmk.tools.addon.data.port.Port;
import com.gni.frmk.tools.addon.data.scheduler.Scheduler;
import com.gni.frmk.tools.addon.data.trigger.JmsAlias;
import com.gni.frmk.tools.addon.data.trigger.JmsTrigger;
import com.gni.frmk.tools.addon.data.trigger.NativeTrigger;
import com.gni.frmk.tools.addon.data.trigger.Trigger;
import com.gni.frmk.tools.addon.invoke.ServiceException;
import com.gni.frmk.tools.addon.invoke.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootNativeInvoker;

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
            switch (visited.getState().getEnableStatus()) {
                case ENABLED:
                    artInvoker.enableConnection(visited.getInfos().getAlias());
                    break;
                case DISABLED:
                    artInvoker.disableConnection(visited.getInfos().getAlias());
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
            ActivableComponentState.ActiveStatus state = visited.getState().getActiveStatus();
            ComponentState.EnableStatus status = visited.getState().getEnableStatus();
            switch (state) {
                case ACTIVE:
                    artInvoker.resumeListener(visited.getInfos().getName());
                    break;
                case SUSPENDED:
                    artInvoker.suspendListener(visited.getInfos().getName());
                    break;
            }
            switch (status) {
                case ENABLED:
                    artInvoker.enableListener(visited.getInfos().getName());
                    break;
                case DISABLED:
                    artInvoker.disableListener(visited.getInfos().getName());
                    break;
            }
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(AdapterNotification visited) throws ConfigurationVisitorException {
        try {
            ActivableComponentState.ActiveStatus state = visited.getState().getActiveStatus();
            ComponentState.EnableStatus status = visited.getState().getEnableStatus();
            switch (state) {
                case ACTIVE:
                    artInvoker.resumeNotification(visited.getInfos().getName());
                    break;
                case SUSPENDED:
                    artInvoker.suspendNotification(visited.getInfos().getName());
                    break;
            }
            switch (status) {
                case ENABLED:
                    artInvoker.enableNotification(visited.getInfos().getName());
                    break;
                case DISABLED:
                    artInvoker.disableNotification(visited.getInfos().getName());
                    break;
            }
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Port visited) throws ConfigurationVisitorException {
        try {
            switch (visited.getState().getEnableStatus()) {
                case ENABLED:
                    rootInvoker.enablePortListener(visited.getId().getKey(), visited.getInfos().getPackageName());
                    break;
                case DISABLED:
                    rootInvoker.disablePortListener(visited.getId().getKey(), visited.getInfos().getPackageName());
                    break;
                default:
                    throw new IllegalStateException("unknown state");
            }
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
    }

    public void visit(Scheduler visited) throws ConfigurationVisitorException {
        //TODO  a corriger
//        try {
//            Scheduler.ExecutionState execState = visited.getExecutionState();
//            Scheduler.SchedulerState scheduleState = visited.getSchedulerState();
//            switch (execState) {
//                case READY:
//                    rootInvoker.wakeupUserTask(visited.getOid());
//                    break;
//                case SUSPENDED:
//                    rootInvoker.suspendUserTask(visited.getOid());
//                    break;
//                default:
//                    throw new IllegalStateException("unknown state");
//            }
//        } catch (ServiceException e) {
//            throw new ConfigurationVisitorException(visited, e);
//        }
    }

    public void visit(JmsAlias visited) throws ConfigurationVisitorException {
        try {
            ComponentState.EnableStatus enableStatus = visited.getState().getEnableStatus();
            switch (enableStatus) {
                case ENABLED:
                    jmsInvoker.enableConnectionAlias(visited.getId().getKey());
                    break;
                case DISABLED:
                    jmsInvoker.disableConnectionAlias(visited.getId().getKey());
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
            Trigger.Status status = visited.getStatus();
            Trigger.State state = visited.getExecutionState();
            switch (status) {
                case ENABLED:
                    jmsInvoker.enableJMSTriggers(visited.getName());
                    break;
                case DISABLED:
                    jmsInvoker.disableJMSTriggers(visited.getName());
                    break;
            }
            switch (state) {
                case ACTIVE:
                    jmsInvoker.enableJMSTriggers(visited.getName());
                    break;
                case SUSPENDED:
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
        NativeTrigger.NativeState processingState = visited.getProcessingState();
        boolean processingSuspended = processingState.isSuspended();
        boolean processingPermanent = processingState.isPermanent();
        if (processingSuspended && !processingPermanent) {
            processingPermanent = true;
        }
        try {
            rootInvoker.suspendTriggers(true, processingSuspended, processingPermanent, visited.getName());
        } catch (ServiceException e) {
            throw new ConfigurationVisitorException(visited, e);
        }
        NativeTrigger.NativeState retrievalState = visited.getRetrievalState();
        boolean retrievalSuspended = retrievalState.isSuspended();
        boolean retrievalPermanent = retrievalState.isPermanent();
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