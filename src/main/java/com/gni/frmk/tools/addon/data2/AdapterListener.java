package com.gni.frmk.tools.addon.data2;

import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 18:29
 * To change this template use File | Settings | File Templates.
 */
public class AdapterListener extends AdapterTypeComponent {

    public static final ComponentType TYPE = ComponentType.ADAPTER_LISTENER;
    private static final String LISTENER_NAME_KEY = "listenerName";

    private final EnableComponentState state;

    private AdapterListener(Builder<?, ?> builder) {
        super(builder);
        state = builder.getState();
    }

    /**
     * empty constructor for jaxb.
     */
    private AdapterListener() {
        state = null;
    }

    public EnableComponentState getState() {
        return state;
    }

    @XmlTransient
    public String getListenerName() {
        return findRequiredDetail(LISTENER_NAME_KEY).getValue();
    }

    public void setListenerName(String alias) {
        addDetail(new ComponentDetail(LISTENER_NAME_KEY, alias));
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static AdapterListenerBuilder builder() {
        return new AdapterListenerBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState, T extends Builder<S, T>> extends AdapterTypeComponent.Builder<EnableComponentState, T> {

        private String listenerName;

        public Builder() {
            type(TYPE);
        }

        public T listenerName(String value) {
            listenerName = checkNotNull(value, "listenerName required");
            id(value);
            addDetail(LISTENER_NAME_KEY, listenerName);
            return self();
        }

        @Override
        public T check() throws CheckException {
            super.check();
            try {
                checkNotNull(listenerName, "listenerName required");
            } catch (NullPointerException npex) {
                throw new CheckException(npex);
            }
            return self();
        }

        @Override
        public AdapterListener build() {
            return new AdapterListener(this);
        }

    }

    public static class AdapterListenerBuilder extends Builder<EnableComponentState, AdapterListenerBuilder> {
        @Override
        protected AdapterListenerBuilder self() {
            return this;
        }
    }

}
