package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisitor;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
@XmlRootElement
public class Port extends PackageAware<StringId, ActivableState> {

    @NotNull
    @XmlElement
    private final String key;

    @NotNull
    @XmlElement
    private final boolean primary;

    public Port(PortBuilder builder) {
        super(builder);
        key = builder.key;
        primary=builder.primary;
    }

    private Port(){
        super();
        key=null;
        primary=false;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
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
        if (o instanceof Port) {
            Port other = (Port) o;
            return Objects.equal(getKey(), other.getKey());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey());
    }

    public static PortBuilder builder() {
        return new PortBuilder();
    }

    public static class PortBuilder extends PackageAware.Builder<PortBuilder, Port, StringId, ActivableState> {

        @NotNull
        protected String key;

        protected boolean primary=false;

        public PortBuilder() {
            defineType(ComponentType.PORT);
        }

        @Override
        public PortBuilder self() {
            return this;
        }

        public PortBuilder key(String value) {
            key = checkNotNull(value);
            defineId(new StringId(key));
            return self();
        }

        public PortBuilder primary(boolean value) {
            primary = value;
            return self();
        }

        @Override
        protected Port buildObjectBeforeValidation() {
            return new Port(this);
        }

    }
}
