package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.ImmutableIntegrationServerPackage.MutableIntegrationServerPackage;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.EnableState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:19
 *
 * @author: e03229
 */
@XmlRootElement
public class ImmutableIntegrationServerPackage
        extends ImmutablePackageAware<ImmutableIntegrationServerPackage, MutableIntegrationServerPackage, StringId, EnableState>
        implements ReadableIntegrationServerPackage {

    public ImmutableIntegrationServerPackage(ReadableIntegrationServerPackage source) {
        super(source);
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public ImmutableIntegrationServerPackage toImmutable() {
        return this;
    }

    @Override
    public MutableIntegrationServerPackage toMutable() {
        return new MutableIntegrationServerPackage(this);
    }

    public static class MutableIntegrationServerPackage
            extends MutablePackageAware<ImmutableIntegrationServerPackage, MutableIntegrationServerPackage, StringId, EnableState>
            implements WritableIntegrationServerPackage {

        public MutableIntegrationServerPackage(ReadableIntegrationServerPackage source) {
            super(source);
            setType(ComponentType.IS_PACKAGE);
        }

        public MutableIntegrationServerPackage() {
            setType(ComponentType.IS_PACKAGE);
        }

        @Override
        public void setPackageName(String value) {
            setComponentId(new StringId(value));
            super.setPackageName(value);
        }

        @Override
        public ImmutableIntegrationServerPackage toImmutable() {
            return new ImmutableIntegrationServerPackage(this);
        }

        @Override
        public MutableIntegrationServerPackage toMutable() {
            return new MutableIntegrationServerPackage(this);
        }

        @Override
        public void accept(ConfigurationVisitor visitor) {
            visitor.visitComponent(toImmutable());
        }
    }
}
