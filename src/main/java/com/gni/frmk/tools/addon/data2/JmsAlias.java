package com.gni.frmk.tools.addon.data2;

import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class JmsAlias extends AbstractComponent {

    public static final ComponentType TYPE = ComponentType.JMS_ALIAS;
    private static final String DESCRIPTION_KEY = "description";

    private final EnableComponentState state;

    private JmsAlias(Builder<?, ?> builder) {
        super(builder);
        state = builder.getState();
    }

    /**
     * empty constructor for jaxb.
     */
    private JmsAlias() {
        state = null;
    }

    public EnableComponentState getState() {
        return state;
    }

    public String getDescription() {
        return findRequiredDetail(DESCRIPTION_KEY).getValue();
    }

    public void setDescription(String description) {
        addDetail(new ComponentDetail(DESCRIPTION_KEY, description));
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static JmsAliasBuilder builder() {
        return new JmsAliasBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState, T extends Builder<S, T>> extends AbstractComponent.Builder<EnableComponentState, T> {

        private String description;

        public T description(String value) {
            description = checkNotNull(value, "description required");
            addDetail(DESCRIPTION_KEY, description);
            return self();
        }

        public Builder() {
            type(TYPE);
        }

        @Override
        public JmsAlias build() {
            return new JmsAlias(this);
        }
    }

    public static class JmsAliasBuilder extends Builder<EnableComponentState, JmsAliasBuilder> {
        @Override
        protected JmsAliasBuilder self() {
            return this;
        }
    }

}
