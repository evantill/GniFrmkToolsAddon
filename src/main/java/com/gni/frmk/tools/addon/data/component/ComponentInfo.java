package com.gni.frmk.tools.addon.data.component;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Constraint;
import com.google.common.collect.Constraints;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 */
@XmlType
public class ComponentInfo<C extends ComponentInfo> implements Comparable<C> {

    @XmlType
     public static final class HashMapInfoEntryType {
        @XmlAttribute(required = true)
        private final String key;
        @XmlValue
        public final String value;

         public HashMapInfoEntryType(String key, String value) {
             this.key = key;
             this.value = value;
         }

           /**
         * Empty constructor only used by jaxb
         */
        private HashMapInfoEntryType() {
            this.key = null;
            this.value = null;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
     }

    public static final class HashMapInfoTypeAdapter extends XmlAdapter<ArrayList<HashMapInfoEntryType>, Map<String, String>> {

        @Override
        public Map<String, String> unmarshal(ArrayList<HashMapInfoEntryType> elements) throws Exception {
            Map<String, String> infos = Maps.newHashMap();
            for (HashMapInfoEntryType info: elements) {
                infos.put(info.getKey(), info.getValue());
            }
            return infos;
        }

        @Override
        public ArrayList<HashMapInfoEntryType> marshal(Map<String, String> infos) throws Exception {
            ArrayList<HashMapInfoEntryType>  elements =Lists.newArrayList();
            for (Map.Entry<String, String> info : infos.entrySet()) {
                elements.add(new HashMapInfoEntryType(info.getKey(), info.getValue()));
            }
            return elements;
        }
    }

    Constraint<String> requiredValue = Constraints.notNull();

    @XmlElement
    @XmlJavaTypeAdapter(HashMapInfoTypeAdapter.class)
    private /*final*/ Map<String, String> infos;

    public ComponentInfo() {
        this.infos = new HashMap<String, String>();
    }

    public ComponentInfo(Map<String, String> infos) {
        this.infos = infos;
    }

    public String getInfo(String key) {
        return infos.get(key);
    }

    public String getRequiredInfo(String key) {
        return requiredValue.checkElement(infos.get(key));
    }

    public void addInfo(String key, String value) {
        infos.put(checkNotNull(key), value);
    }

    public Map<String, String> getInfos() {
        return infos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ComponentInfo that = (ComponentInfo) o;

        return Objects.equal(getInfos(), that.getInfos());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getInfos());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("infos", getInfos()).toString();
    }

    public int compareTo(C that) {
        return ComparisonChain.start().compare(getInfos().hashCode(), that.getInfos().hashCode()).result();
    }
}
