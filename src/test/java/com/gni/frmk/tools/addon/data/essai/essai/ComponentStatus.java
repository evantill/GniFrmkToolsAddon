package com.gni.frmk.tools.addon.data.essai.essai;

import com.google.common.base.Objects;

import static com.gni.frmk.tools.addon.data.essai.essai.ComponentStatus.Temporal.PERMANENT;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 04/11/10
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public class ComponentStatus {
    public enum Status {UNKNOWN, ON, OFF}

    public enum Temporal {UNKNOWN, TEMPORARY, PERMANENT}

    private final Status status;
    private final Temporal temporal;

    public ComponentStatus(Status status, Temporal temporal) {
        this.status = status;
        this.temporal = temporal;
    }

    public ComponentStatus(Status status) {
        this.status = status;
        this.temporal = PERMANENT;
    }

    public Status getStatus() {
        return status;
    }

    public Temporal getTemporal() {
        return temporal;
    }

    public ComponentStatus changeStatus(final Status newStatus) {
        return new ComponentStatus(newStatus, getTemporal());
    }

    public ComponentStatus changeTemporal(final Temporal newTemporal) {
        return new ComponentStatus(getStatus(), newTemporal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentStatus that = (ComponentStatus) o;
        return Objects.equal(that.getStatus(), getStatus()) && Objects.equal(that.getTemporal(), getTemporal());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status, temporal);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("status", status).add("temporal", temporal).toString();
    }
}
