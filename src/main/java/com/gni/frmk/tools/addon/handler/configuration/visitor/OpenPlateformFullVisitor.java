package com.gni.frmk.tools.addon.handler.configuration.visitor;

import com.gni.frmk.tools.addon.action.wm.art.connection.EnableConnection;
import com.gni.frmk.tools.addon.action.wm.art.listener.EnableListener;
import com.gni.frmk.tools.addon.action.wm.art.notifications.EnableNotification;
import com.gni.frmk.tools.addon.action.wm.jms.alias.EnableJmsAlias;
import com.gni.frmk.tools.addon.action.wm.jms.trigger.EnableJmsTriggers;
import com.gni.frmk.tools.addon.action.wm.root.port.EnablePortListener;
import com.gni.frmk.tools.addon.action.wm.root.scheduler.WakeUpUserTask;
import com.gni.frmk.tools.addon.action.wm.root.trigger.SuspendTriggers;
import com.gni.frmk.tools.addon.api.Producer;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.google.common.collect.Lists;

import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/04/11
 * Time: 08:39
 *
 * @author: e03229
 */
public class OpenPlateformFullVisitor
        extends IgnoreComponentConfigurationVisitor
        implements ConfigurationVisitor, Producer<List<Action<?>>> {

    private final List<Action<?>> openInputActions = Lists.newArrayList();
    private final List<Action<?>> openOutputActions = Lists.newArrayList();

    @Override
    public void dispatchVisit(ConfigurationVisited visitable) {
        openInputActions.clear();
        openOutputActions.clear();
        visitable.accept(this);
    }

    @Override
    public void visitComponent(AdapterConnection visited) {
        openOutput(new EnableConnection(visited.getAlias()));
    }

    @Override
    public void visitComponent(AdapterListener visited) {
        openInput(new EnableListener(visited.getName()));
    }

    @Override
    public void visitComponent(AdapterNotification visited) {
        openInput(new EnableNotification(visited.getName()));
    }

    @Override
    public void visitComponent(IntegrationServerPackage visited) {
        //TODO a implementer dans un deuxieme temps
    }

    @Override
    public void visitComponent(Port visited) {
        openInput(new EnablePortListener(visited.getPackageName(), visited.getKey()));
    }

    @Override
    public void visitComponent(Scheduler visited) {
        openInput(new WakeUpUserTask(visited.getOid()));
    }

    @Override
    public void visitComponent(NativeTrigger visited) {
        SuspendTriggers action = SuspendTriggers.builder()
                                                .addTriggerName(visited.getName())
                                                .applyChangeAcrossCluster(false)
                                                .persistChange(true)
                                                .suspendProcessing(false)
                                                .suspendRetrieval(false)
                                                .build();
        openInput(action);
    }

    @Override
    public void visitComponent(JmsTrigger visited) {
        openInput(new EnableJmsTriggers(visited.getName()));
    }

    @Override
    public void visitComponent(JmsAlias visited) {
        openOutput(new EnableJmsAlias(visited.getName()));
    }

    @Override
    public void visitConfiguration(Configuration configuration) {
        //TODO peut etre modifier la date de derniere modification de la configuration
    }

    @Override
    public List<Action<?>> produce() {
        List<Action<?>> produced = Lists.newArrayList();
        produced.addAll(openOutputActions);
        produced.addAll(openInputActions);
        return unmodifiableList(produced);
    }

    private void openInput(Action<?> action) {
        openInputActions.add(action);
    }

    private void openOutput(Action<?> action) {
        openOutputActions.add(action);
    }

}
