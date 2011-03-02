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

    private Port(Builder<?> builder) {
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

    public static Builder<PortBuilder> builder() {
        return new PortBuilder();
    }

    public static abstract class Builder<T extends Builder<T>> extends PackageComponent.Builder<T> {

        public Builder() {
            type(TYPE);
        }

        @Override
        public Port build() {
            return new Port(this);
        }

    }

    public static class PortBuilder extends Builder<PortBuilder> {
        @Override
        protected PortBuilder self() {
            return this;
        }
    }

}
