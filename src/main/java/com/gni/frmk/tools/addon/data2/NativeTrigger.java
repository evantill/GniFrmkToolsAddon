package com.gni.frmk.tools.addon.data2;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class NativeTrigger extends AbstractComponent {

    public static final ComponentType TYPE = ComponentType.NATIVE_TRIGGER;

    private final EnableComponentState state;

    private NativeTrigger(Builder<?, ?> builder) {
        super(builder);
        state = builder.getState();
    }

    /**
     * empty constructor for jaxb.
     */
    private NativeTrigger() {
        state = null;
    }

    public EnableComponentState getState() {
        return state;
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static NativeTriggerBuilder builder() {
        return new NativeTriggerBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState, T extends Builder<S, T>> extends AbstractComponent.Builder<EnableComponentState, T> {
        public Builder() {
            type(TYPE);
        }

        @Override
        public NativeTrigger build() {
            return new NativeTrigger(this);
        }
    }

    public static class NativeTriggerBuilder extends Builder<EnableComponentState, NativeTriggerBuilder> {
        @Override
        protected NativeTriggerBuilder self() {
            return this;
        }
    }

}
