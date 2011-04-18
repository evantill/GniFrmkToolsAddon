package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.ImmutablePort.MutablePort;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
@XmlRootElement
public class ImmutablePort
        extends ImmutablePackageAware<ImmutablePort, MutablePort, StringId, ActivableState>
        implements ReadablePort {

    private final String key;
    private final boolean primary;

    public ImmutablePort(ReadablePort source) {
        super(source);
        key = source.getKey();
        primary = source.isPrimary();
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public ImmutablePort toImmutable() {
        return this;
    }

    @Override
    public MutablePort toMutable() {
        return new MutablePort(this);
    }

    public String getKey() {
        return key;
    }

    public boolean isPrimary() {
        return primary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof ImmutablePort) {
            ImmutablePort other = (ImmutablePort) o;
            return Objects.equal(getKey(), other.getKey());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey());
    }

    public static class MutablePort
            extends MutablePackageAware<ImmutablePort, MutablePort, StringId, ActivableState>
            implements WritablePort {

        private String key;
        private boolean primary;

        public MutablePort(ReadablePort source) {
            super(source);
            setKey(source.getKey());
            setPrimary(source.isPrimary());
            setType(ComponentType.PORT);
        }

        public MutablePort() {
            setType(ComponentType.PORT);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            setComponentId(new StringId(key));
            this.key = key;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }

        @Override
        public ImmutablePort toImmutable() {
            return new ImmutablePort(this);
        }

        @Override
        public MutablePort toMutable() {
            return new MutablePort(this);
        }

        @Override
        public void accept(ConfigurationVisitor visitor) {
            visitor.visitComponent(this.toImmutable());
        }
    }

}
