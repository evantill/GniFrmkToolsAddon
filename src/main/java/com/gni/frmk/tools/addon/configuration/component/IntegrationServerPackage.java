package com.gni.frmk.tools.addon.configuration.component;

import com.gni.frmk.tools.addon.configuration.visitor.ComponentVisitor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:19
 *
 * @author: e03229
 */
@XmlRootElement
public class IntegrationServerPackage extends PackageAware<StringId, EnableState> {

    public IntegrationServerPackage(Builder<?, ?, StringId, EnableState> builder) {
        super(builder);
    }

    private IntegrationServerPackage(){
        super();
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static IntegrationServerPackageBuilder builder() {
        return new IntegrationServerPackageBuilder();
    }

    public static class IntegrationServerPackageBuilder extends Builder<IntegrationServerPackageBuilder, IntegrationServerPackage, StringId, EnableState> {

        public IntegrationServerPackageBuilder() {
            defineType(ComponentType.IS_PACKAGE);
        }

        @Override
        public IntegrationServerPackageBuilder self() {
            return this;
        }

        @Override
        protected IntegrationServerPackage buildObjectBeforeValidation() {
            return new IntegrationServerPackage(this);
        }

        @Override
        public Builder<IntegrationServerPackageBuilder, IntegrationServerPackage, StringId, EnableState> packageName(String value) {
            super.packageName(value);
            defineId(new StringId(packageName));
            return self();
        }
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends PackageAware<I, S>, I extends StringId, S extends EnableState>
            extends PackageAware.Builder<T, B, I, S> {
    }
}
