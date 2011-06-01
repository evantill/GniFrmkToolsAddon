package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentStateType;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;
import java.util.Map;

import static java.util.Collections.sort;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:03
 *
 * @author: e03229
 */
public final class XmlJaxbAdapters {

    public static class StatesMap {
        private List<StateEntry> entries = Lists.newArrayList();

        public StatesMap() {
        }

        @XmlElement(name = "stateConfiguration")
        public List<StateEntry> getEntries() {
            return entries;
        }

        public void setEntries(List<StateEntry> entries) {
            this.entries = entries;
        }
    }

    @XmlType(propOrder = {"type",
                          "state"})
    public static class StateEntry
            implements Comparable<StateEntry> {
        private ComponentStateType type;
        private ComponentState state;

        public StateEntry(ComponentStateType type, ComponentState state) {
            this.type = type;
            this.state = state;
        }

        public StateEntry() {
        }

        @XmlAttribute(required = true)
        public ComponentStateType getType() {
            return type;
        }

        public void setType(ComponentStateType type) {
            this.type = type;
        }

        @XmlElement(type = BaseComponentState.class, required = true)
        public ComponentState getState() {
            return state;
        }

        public void setState(ComponentState state) {
            this.state = state;
        }

        @Override
        public int compareTo(StateEntry o) {
            return ComparisonChain.start().compare(getType(), o.getType()).result();
        }
    }

    public static class ComponentStatesAdapter
            extends XmlAdapter<StatesMap, Map<ComponentStateType, ComponentState>> {

        @Override
        public Map<ComponentStateType, ComponentState> unmarshal(StatesMap v) throws Exception {
            Map<ComponentStateType, ComponentState> states = Maps.newHashMap();
            for (StateEntry state : v.getEntries()) {
                states.put(state.getType(), state.getState());
            }
            return states;
        }

        @Override
        public StatesMap marshal(Map<ComponentStateType, ComponentState> v) throws Exception {
            StatesMap states = new StatesMap();
            for (Map.Entry<ComponentStateType, ComponentState> entry : v.entrySet()) {
                states.getEntries().add(new StateEntry(entry.getKey(), entry.getValue()));
            }
            sort(states.getEntries());
            return states;
        }
    }
}
