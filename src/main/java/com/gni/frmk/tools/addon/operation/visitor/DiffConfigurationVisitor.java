package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.Configuration;
import com.gni.frmk.tools.addon.data.ConfigurationElement;
import com.gni.frmk.tools.addon.data.adapter.*;
import com.gni.frmk.tools.addon.data.component.*;
import com.gni.frmk.tools.addon.data.port.Port;
import com.gni.frmk.tools.addon.data.scheduler.Scheduler;
import com.gni.frmk.tools.addon.data.trigger.JmsAlias;
import com.gni.frmk.tools.addon.data.trigger.JmsAliasInfo;
import com.gni.frmk.tools.addon.data.trigger.JmsTrigger;
import com.gni.frmk.tools.addon.data.trigger.NativeTrigger;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class DiffConfigurationVisitor implements ConfigurationVisitor {
    private final Configuration reference;
    private final Configuration difference;

    //TODO supprimer les references a ELEMENT apres migration datas
    private final class SameElementInSameStatus<T extends ConfigurationElement> implements Predicate<T> {
        final T visited;

        public SameElementInSameStatus(T visited) {
            this.visited = visited;
        }

        public boolean apply(T element) {
            return visited.equals(element) && visited.compareStatusTo(element) != 0;
        }
    }

    //TODO simplification des typages generiques ???
    private final class SameComponentInSameStatus<T extends Component<F, S>, F extends ComponentInfo, S extends ComponentState> implements Predicate<T> {
        final T visited;

        public SameComponentInSameStatus(T visited) {
            this.visited = visited;
        }

        public boolean apply(T element) {
            return visited.equals(element) && visited.compareStatusTo(element) != 0;
        }
    }

    public DiffConfigurationVisitor(Configuration reference) {
        this.reference = reference;
        difference = new Configuration(reference.getName());
    }

    public void visit(final AdapterConnection visited) {
        Set<AdapterConnection> references = reference.getAdapterConnectionList();
        AdapterConnection searchResult = Iterables.find(references, new SameComponentInSameStatus<AdapterConnection, AdapterConnectionInfo, ComponentState>(visited), null);
        if (searchResult != null) {
            difference.getAdapterConnectionList().add(visited);
        }
    }

    public void visit(final AdapterListener visited) {
        Set<AdapterListener> references = reference.getAdapterListenerList();
        AdapterListener searchResult = Iterables.find(references, new SameComponentInSameStatus<AdapterListener, AdapterListenerInfo, ActivableComponentState>(visited), null);
        if (searchResult != null) {
            difference.getAdapterListenerList().add(visited);
        }
    }

    public void visit(final AdapterNotification visited) {
        Set<AdapterNotification> references = reference.getAdapterNotificationList();
        AdapterNotification searchResult = Iterables.find(references, new SameComponentInSameStatus<AdapterNotification, AdapterNotificationInfo, ActivableComponentState>(visited), null);
        if (searchResult != null) {
            difference.getAdapterNotificationList().add(visited);
        }
    }

    public void visit(final Port visited) {
        Set<Port> references = reference.getPortList();
        Port searchResult = Iterables.find(references, new SameComponentInSameStatus<Port, ComponentPackageInfo, ActivableComponentState>(visited), null);
        if (searchResult != null) {
            difference.getPortList().add(visited);
        }
    }

    public void visit(final Scheduler visited) {
        Set<Scheduler> references = reference.getSchedulerList();
        //TODO  a corriger
//        Scheduler searchResult = Iterables.find(references, new SameElementInSameStatus<Scheduler>(visited), null);
//        if (searchResult != null) {
//            difference.getSchedulerList().add(visited);
//        }
    }

    public void visit(final JmsAlias visited) {
        Set<JmsAlias> references = reference.getJmsAliasList();
        JmsAlias searchResult = Iterables.find(references, new SameComponentInSameStatus<JmsAlias, JmsAliasInfo, ActivableComponentState>(visited), null);
        if (searchResult != null) {
            difference.getJmsAliasList().add(visited);
        }
    }

    public void visit(final JmsTrigger visited) {
        Set<JmsTrigger> references = reference.getJmsTriggerList();
        JmsTrigger searchResult = Iterables.find(references, new SameElementInSameStatus<JmsTrigger>(visited), null);
        if (searchResult != null) {
            difference.getJmsTriggerList().add(visited);
        }
    }

    public void visit(final NativeTrigger visited) {
        Set<NativeTrigger> references = reference.getNativeTriggerList();
        NativeTrigger searchResult = Iterables.find(references, new SameElementInSameStatus<NativeTrigger>(visited), null);
        if (searchResult != null) {
            difference.getNativeTriggerList().add(visited);
        }
    }

    public void clear() {
        difference.clear();
    }

    public Configuration getReference() {
        return reference;
    }

    public Configuration getDifference() {
        return difference;
    }
}
