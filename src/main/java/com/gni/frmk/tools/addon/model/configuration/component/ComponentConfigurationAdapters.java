package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.configuration.component.ComponentConfiguration.ComponentStateContext;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static com.google.common.collect.Maps.newTreeMap;
import static com.google.common.collect.Sets.newTreeSet;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/03/11
 * Time: 17:31
 *
 * @author: e03229
 */
public class ComponentConfigurationAdapters {

    public static class ComponentStatesAdapter
            extends XmlAdapter<StatesType, Map<ComponentStateContext, AbstractComponentState>> {

        @Override
        public Map<ComponentStateContext, AbstractComponentState> unmarshal(StatesType v) throws Exception {
            Map<ComponentStateContext, AbstractComponentState> map = newTreeMap();
            for (StateType e : v.states) {
                map.put(e.context, e.state);
            }
            return map;
        }

        @Override
        public StatesType marshal(Map<ComponentStateContext, AbstractComponentState> map) throws Exception {

            StatesType elements = new StatesType();
            for (Entry<ComponentStateContext, AbstractComponentState> next : map.entrySet()) {
                elements.states.add(new StateType(next.getKey(), next.getValue()));
            }
            return elements;
        }
    }

    public static class StatesType {
        @XmlElement(name = "state")
        public Set<StateType> states = newTreeSet();
    }

    public static class StateType implements Comparable<StateType> {
        @XmlAttribute
        public ComponentStateContext context;
        @XmlElementRef
        public AbstractComponentState state;

        public StateType(ComponentStateContext context, AbstractComponentState state) {
            this.context = context;
            this.state = state;
        }

        private StateType() {
        }

        @Override
        public int compareTo(StateType o) {
            return context.compareTo(o.context);
        }
    }

}
