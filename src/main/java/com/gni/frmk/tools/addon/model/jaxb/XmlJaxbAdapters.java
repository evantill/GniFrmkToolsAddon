package com.gni.frmk.tools.addon.model.jaxb;

import com.gni.frmk.tools.addon.model.BaseComponent.AbstractState;
import com.gni.frmk.tools.addon.model.Component.State;
import com.gni.frmk.tools.addon.model.Component.StateType;
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
        private StateType type;
        private State state;

        public StateEntry(StateType type, State state) {
            this.type = type;
            this.state = state;
        }

        public StateEntry() {
        }

        @XmlAttribute(required = true)
        public StateType getType() {
            return type;
        }

        public void setType(StateType type) {
            this.type = type;
        }

        @XmlElement(type = AbstractState.class, required = true)
        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        @Override
        public int compareTo(StateEntry o) {
            return ComparisonChain.start().compare(getType(), o.getType()).result();
        }
    }

    public static class ComponentStatesAdapter
            extends XmlAdapter<StatesMap, Map<StateType, State>> {

        @Override
        public Map<StateType, State> unmarshal(StatesMap v) throws Exception {
            Map<StateType, State> states = Maps.newHashMap();
            for (StateEntry state : v.getEntries()) {
                states.put(state.getType(), state.getState());
            }
            return states;
        }

        @Override
        public StatesMap marshal(Map<StateType, State> v) throws Exception {
            StatesMap states = new StatesMap();
            for (Map.Entry<StateType, State> entry : v.entrySet()) {
                states.getEntries().add(new StateEntry(entry.getKey(), entry.getValue()));
            }
            sort(states.getEntries());
            return states;
        }
    }
}
