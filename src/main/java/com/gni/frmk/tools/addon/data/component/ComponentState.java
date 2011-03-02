package com.gni.frmk.tools.addon.data.component;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 04/11/10
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
@XmlType
public class ComponentState<S extends ComponentState> implements Comparable<S> {
    public static enum EnableStatus {
        ENABLED, DISABLED
    }

    //TODO mutable : doit etre retire des equals hashcode et compare ???
    private EnableStatus enableStatus;

    public ComponentState( EnableStatus enableStatus) {
        this.enableStatus = enableStatus;
    }

      /**
     * Empty constructor used by jaxb.
     */
    private ComponentState(){
        enableStatus=null;
    }

    public EnableStatus getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(EnableStatus enableStatus) {
        this.enableStatus = enableStatus;
    }

    public int compareEnableStatus(ComponentState other) {
        return ComparisonChain.start()
                .compare(getEnableStatus(), other.getEnableStatus())
                .result();
    }

    public int compareTo(S other) {
        return compareEnableStatus(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentState that = (ComponentState) o;
        return Objects.equal(getEnableStatus(), that.getEnableStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEnableStatus());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("enableStatus", getEnableStatus()).toString();
    }
}
