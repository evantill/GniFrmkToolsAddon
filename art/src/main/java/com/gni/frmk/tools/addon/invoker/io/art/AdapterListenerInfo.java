package com.gni.frmk.tools.addon.invoker.io.art;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 13:53
 *
 * @author: e03229
 */
public class AdapterListenerInfo implements Comparable<AdapterListenerInfo> {
    public static enum AdapterListenerState {
        YES(true, true), NO(false, true), SUSPENDED(true, false), UNSCHED(false, false);

        private final boolean enabled;
        private final boolean activated;

        AdapterListenerState(boolean enabled, boolean activated) {
            this.enabled = enabled;
            this.activated = activated;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public boolean isActivated() {
            return activated;
        }
    }

    private final String adapterType;
    private final String notificationNodeName;
    private final String packageName;
    private final AdapterListenerState notificationEnabled;

    public AdapterListenerInfo(Builder builder) {
        adapterType = builder.adapterType;
        notificationNodeName = builder.notificationNodeName;
        packageName = builder.packageName;
        notificationEnabled = builder.notificationEnabled;
    }

    public String getNotificationNodeName() {
        return notificationNodeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public AdapterListenerState getNotificationEnabled() {
        return notificationEnabled;
    }

    public String getAdapterType() {
        return adapterType;
    }

    @Override
    public int compareTo(AdapterListenerInfo o) {
        return ComparisonChain.start()
                              .compare(getAdapterType(), o.getAdapterType())
                              .compare(getNotificationNodeName(), o.getNotificationNodeName())
                              .compare(getPackageName(), o.getPackageName())
                              .compare(getNotificationEnabled(), o.getNotificationEnabled())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdapterListenerInfo that = (AdapterListenerInfo) o;

        return Objects.equal(getAdapterType(), that.getAdapterType())
               && Objects.equal(getNotificationNodeName(), that.getNotificationNodeName())
               && Objects.equal(getNotificationEnabled(), that.getNotificationEnabled())
               && Objects.equal(getPackageName(), that.getPackageName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getAdapterType(), getNotificationNodeName(), getPackageName(), getNotificationEnabled());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String adapterType;
        private String notificationNodeName;
        private String packageName;
        private AdapterListenerState notificationEnabled;

        public Builder adapterType(String value) {
            adapterType = Preconditions.checkNotNull(value);
            return this;
        }

        public Builder notificationNodeName(String value) {
            notificationNodeName = Preconditions.checkNotNull(value);
            return this;
        }

        public Builder packageName(String value) {
            packageName = Preconditions.checkNotNull(value);
            return this;
        }

        public Builder notificationState(String value) {
            notificationEnabled = AdapterListenerState.valueOf(value.toUpperCase());
            return this;
        }

        public AdapterListenerInfo build() {
            return new AdapterListenerInfo(this);
        }
    }
}
