package com.gni.frmk.tools.addon.data2;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class NativeTrigger extends Component {

    public static final ComponentType TYPE = ComponentType.NATIVE_TRIGGER;

    private NativeTrigger(Builder<?> builder) {
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

    public static Builder<NativeTriggerBuilder> builder() {
        return new NativeTriggerBuilder();
    }

    public static abstract class Builder<T extends Builder<T>> extends Component.Builder<T> {
        public Builder() {
            type(TYPE);
        }

        @Override
        public NativeTrigger build() {
            return new NativeTrigger(this);
        }
    }

    public static class NativeTriggerBuilder extends Builder<NativeTriggerBuilder> {
        @Override
        protected NativeTriggerBuilder self() {
            return this;
        }
    }

}
