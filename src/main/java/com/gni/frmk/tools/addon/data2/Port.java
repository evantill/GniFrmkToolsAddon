package com.gni.frmk.tools.addon.data2;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 02/03/11
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
public class Port extends PackageComponent<EnableComponentState> {

    public static final ComponentType TYPE = ComponentType.PORT;

    private Port(Builder<? extends EnableComponentState,?> builder) {
        super(builder);
    }

    /**
     * empty constructor for jaxb.
     */
    private Port() {
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static PortBuilder builder() {
        return new PortBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState, T extends Builder<S, T>> extends PackageComponent.Builder<EnableComponentState,T> {

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
