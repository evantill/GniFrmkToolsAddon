package com.gni.frmk.tools.addon.data2;

import javax.xml.bind.annotation.XmlAttribute;
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
public class ComponentDetail<D extends ComponentDetail> implements Comparable<D> {

    @XmlAttribute
    private final String key;
    @XmlValue
    private final String value;

    public ComponentDetail(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ComponentDetail(Builder builder) {
        key = builder.key;
        value = builder.value;
    }

    /**
     * empty constructor for jaxb.
     */
    protected ComponentDetail() {
        key = null;
        value = null;
    }

    public ComponentDetail(ComponentDetail src) {
        key = src.getKey();
        value = src.getValue();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static class Builder {
        private String key;
        private String value;

        ComponentDetail build() {
            return new ComponentDetail(this);
        }

        Builder define(String key, String value) {
            this.key = key;
            this.value = value;
            return this;
        }
    }

    public int compareTo(D other) {
        return getKey().compareTo(other.getKey());
    }
}
