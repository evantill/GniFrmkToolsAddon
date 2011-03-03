package com.gni.frmk.tools.addon.data2;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class NativeTrigger extends Component<EnableComponentState> {

    public static final ComponentType TYPE = ComponentType.NATIVE_TRIGGER;

    private NativeTrigger(Builder<? extends EnableComponentState,?> builder) {
        super(builder);
    }

    /**
     * empty constructor for jaxb.
     */
    private NativeTrigger() {
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static NativeTriggerBuilder builder() {
        return new NativeTriggerBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState, T extends Builder<S, T>> extends Component.Builder<EnableComponentState,T> {
        public Builder() {
            type(TYPE);
        }

        @Override
        public NativeTrigger build() {
            return new NativeTrigger(this);
        }
    }

    public static class NativeTriggerBuilder extends Builder<EnableComponentState,NativeTriggerBuilder> {
        @Override
        protected NativeTriggerBuilder self() {
            return this;
        }
    }

}
