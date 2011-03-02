package com.gni.frmk.tools.addon.data2;

import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 09:32
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class ComponentId implements Comparable<ComponentId>{

    @XmlValue
    private final String id;

    public ComponentId(String id) {
        this.id = id;
    }

    /**
     * empty constructor for jaxb.
     */
    private ComponentId() {
        id = null;
    }

    @Override
    public String toString() {
        return id;
    }

    public String getId() {
        return id;
    }

    public int compareTo(ComponentId componentId) {
        return ComparisonChain.start().compare(getId(),componentId.getId()).result();
    }
}
