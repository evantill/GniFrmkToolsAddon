package com.gni.frmk.tools.addon.data2;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
public class Port extends PackageComponent {

    public static final ComponentType TYPE = ComponentType.PORT;

    private final  EnableComponentState state;

    private Port(Builder<?,?> builder) {
        super(builder);
        state = builder.getState();
    }

    /**
     * empty constructor for jaxb.
     */
    private Port() {
        state = null;
    }

    public EnableComponentState getState() {
        return state;
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static PortBuilder builder() {
        return new PortBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState, T extends Builder<S, T>> extends PackageComponent.Builder<S,T> {

        public Builder() {
            type(TYPE);
        }

        @Override
        public Port build() {
            return new Port(this);
        }

    }

    public static class PortBuilder extends Builder<EnableComponentState,PortBuilder> {
        @Override
        protected PortBuilder self() {
            return this;
        }
    }

}
