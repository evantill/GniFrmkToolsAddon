package com.gni.frmk.tools.addon.data.component;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Objects.firstNonNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 04/11/10
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
@XmlType
public class ComponentId implements Comparable<ComponentId> {

    @XmlAttribute(required=true)
    private final String key;

    public ComponentId(String key) {
        this.key = key;
    }

    /**
     * Empty constructor used by jaxb.
     */
    private ComponentId(){
        key=null;
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentId that = (ComponentId) o;
        return Objects.equal(getKey(), that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("key", key).toString();
    }

    public int compareTo(ComponentId that) {
        if(that==null || getKey()==null){
            return -1;
        }
        return ComparisonChain.start()
                .compare(getKey(), that.getKey())
                .result();
    }
}
