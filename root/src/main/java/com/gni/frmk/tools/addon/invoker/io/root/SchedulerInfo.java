package com.gni.frmk.tools.addon.invoker.io.root;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 18:02
 *
 * @author: e03229
 */
public class SchedulerInfo implements Comparable<SchedulerInfo> {
    private final String oid;
    private final String service;
    private final String name;
    private final String type;
    private final String description;
    private final boolean suspended;
    private final boolean expired;
    private final boolean neverRun;

    private SchedulerInfo(Builder builder) {
        oid = builder.oid;
        service = builder.service;
        name = builder.name;
        type = builder.type;
        description = builder.description;
        suspended = builder.suspended;
        expired = builder.expired;
        neverRun = builder.neverRun;
    }

    @Override
    public int compareTo(SchedulerInfo o) {
        return ComparisonChain.start()
                              .compare(getOid(), o.getOid())
                              .compare(getService(), o.getService())
                              .compare(getName(), o.getName())
                              .compare(getType(), o.getType())
                              .compare(getDescription(), o.getDescription())
                              .compare(isSuspended(), o.isSuspended())
                              .compare(isExpired(), o.isExpired())
                              .compare(isNeverRun(), o.isNeverRun())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchedulerInfo that = (SchedulerInfo) o;

        return Objects.equal(getOid(), that.getOid())
               && Objects.equal(getService(), that.getService())
               && Objects.equal(getName(), that.getName())
               && Objects.equal(getType(), that.getType())
               && Objects.equal(getDescription(), that.getDescription())
               && Objects.equal(isSuspended(), that.isSuspended())
               && Objects.equal(isExpired(), that.isExpired())
               && Objects.equal(isNeverRun(), that.isNeverRun());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getOid(), getService(), getName(), getType(), getDescription(), isSuspended(), isExpired(), isNeverRun());
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String oid;
        private String service;
        private String name;
        private String type;
        private String description;
        private boolean suspended;
        private boolean expired;
        private boolean neverRun;

        public SchedulerInfo build() {
            return new SchedulerInfo(this);
        }

        public Builder oid(String value) {
            oid = checkNotNull(value);
            return this;
        }

        public Builder service(String value) {
            service = checkNotNull(value);
            return this;
        }

        public Builder name(String value) {
            name = checkNotNull(value);
            return this;
        }

        public Builder type(String value) {
            type = checkNotNull(value);
            return this;
        }


        public Builder description(String value) {
            description = firstNonNull(value, "");
            return this;
        }

        public Builder suspended(Boolean value) {
            suspended = checkNotNull(value);
            return this;
        }

        public Builder expired(Boolean value) {
            expired = checkNotNull(value);
            return this;
        }

        public Builder neverRun(Boolean value) {
            neverRun = checkNotNull(value);
            return this;
        }
    }

    public String getOid() {return oid;}

    public String getService() {return service;}

    public String getName() {return name;}

    public String getType() {return type;}

    public String getDescription() {return description;}

    public boolean isSuspended() {return suspended;}

    public boolean isExpired() {return expired;}

    public boolean isNeverRun() {return neverRun;}

}
