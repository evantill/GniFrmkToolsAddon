package com.gni.frmk.tools.addon.data3.configuration;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public class ComponentDescriptionAdapters {

    public static class InformationsAdapter extends XmlAdapter<InformationsType, Map<String, String>> {
        @Override
    public Map<String, String> unmarshal(InformationsType infos) throws Exception {
        Map<String, String> map = newHashMap();
        for (InformationType e : infos.information) {
            map.put(e.key, e.value);
        }
        return map;
    }

    @Override
    public InformationsType marshal(Map<String, String> map) throws Exception {
        InformationsType elements = new InformationsType();
        for (Entry<String, String> next : map.entrySet()) {
            elements.information.add(new InformationType(next.getKey(), next.getValue()));
        }
        return elements;
    }
    }

    public static class InformationsType {
        public List<InformationType> information = new ArrayList<InformationType>();
    }

    public static class InformationType {
        @XmlAttribute
        public String key;
        @XmlValue
        public String value;

        public InformationType(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private InformationType() {
        }
    }

    private ComponentDescriptionAdapters() {}
}
