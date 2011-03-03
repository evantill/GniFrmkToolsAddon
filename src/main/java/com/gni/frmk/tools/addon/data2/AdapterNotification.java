package com.gni.frmk.tools.addon.data2;

import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class AdapterNotification extends AdapterTypeComponent {

    public static final ComponentType TYPE = ComponentType.ADAPTER_NOTIFICATION;
    private static final String NOTIFICATION_NAME_KEY = "notificationName";

    private final EnableComponentState state;

    private AdapterNotification(Builder<?, ?> builder) {
        super(builder);
        state=builder.getState();
    }

    /**
     * empty constructor for jaxb.
     */
    private AdapterNotification() {
        state = null;
    }

    public EnableComponentState getState() {
        return state;
    }

    @XmlTransient
    public String getListenerName() {
        return findRequiredDetail(NOTIFICATION_NAME_KEY).getValue();
    }

    public void setListenerName(String alias) {
        addDetail(new ComponentDetail(NOTIFICATION_NAME_KEY, alias));
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static AdapterNotificationBuilder builder() {
        return new AdapterNotificationBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState, T extends Builder<S, T>> extends AdapterTypeComponent.Builder<EnableComponentState, T> {

        private String notificationName;

        public Builder() {
            type(TYPE);
        }

        public T notificationName(String value) {
            notificationName = checkNotNull(value, "notificationName required");
            id(value);
            addDetail(NOTIFICATION_NAME_KEY, notificationName);
            return self();
        }

        @Override
        public T check() throws CheckException {
            super.check();
            try {
                checkNotNull(notificationName, "notification required");
            } catch (NullPointerException npex) {
                throw new CheckException(npex);
            }
            return self();
        }

        @Override
        public AdapterNotification build() {
            return new AdapterNotification(this);
        }

    }

    public static class AdapterNotificationBuilder extends Builder<EnableComponentState, AdapterNotificationBuilder> {
        @Override
        protected AdapterNotificationBuilder self() {
            return this;
        }
    }

}
