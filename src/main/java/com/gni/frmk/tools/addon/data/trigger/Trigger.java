package com.gni.frmk.tools.addon.data.trigger;

import com.google.common.base.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 11:01:12
 * To change this template use File | Settings | File Templates.
 */
public class Trigger {
    public static enum Type {
        NATIVE, JMS
    }

    public static enum State {
        ACTIVE, SUSPENDED
    }

    public static enum Status {
        ENABLED, DISABLED
    }

    public static enum TemporalStatus {
        TEMPORARY, PERMANENT
    }

    protected final String name;
    protected final Type type;
    protected Status status;

    protected Trigger(String name, Status status, Type type) {
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public Trigger(TriggerBuilder builder) {
        name = builder.name;
        type = builder.type;
        status = builder.status;
    }

    /**
     * Empty constructor only for jaxb.
     */
    protected Trigger() {
        name = null;
        type = null;
        status = null;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", getName()).add("type", getType()).add("status", getStatus()).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Trigger that = (Trigger) o;

        return Objects.equal(getType(), that.getType()) && Objects.equal(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getType(), getName());
    }
}
