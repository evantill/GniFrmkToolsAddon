package com.gni.frmk.tools.addon.data2;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class JmsTrigger extends AbstractComponent {

    public static final ComponentType TYPE = ComponentType.JMS_TRIGGER;

    private final EnableComponentState state;

    private JmsTrigger(Builder<?,?> builder) {
        super(builder);
        state=builder.getState();
    }

    /**
     * empty constructor for jaxb.
     */
    private JmsTrigger() {
        state = null;
    }

    public EnableComponentState getState() {
        return state;
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static JmsTriggerBuilder builder() {
        return new JmsTriggerBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState, T extends Builder<S, T>> extends AbstractComponent.Builder<EnableComponentState,T> {
        public Builder() {
            type(TYPE);
        }

        @Override
        public JmsTrigger build() {
            return new JmsTrigger(this);
        }
    }

    public static class JmsTriggerBuilder extends Builder<EnableComponentState,JmsTriggerBuilder> {
        @Override
        protected JmsTriggerBuilder self() {
            return this;
        }
    }

}
