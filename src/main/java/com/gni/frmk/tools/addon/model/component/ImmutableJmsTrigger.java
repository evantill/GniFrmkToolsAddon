package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.ImmutableJmsTrigger.MutableJmsTrigger;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
@XmlRootElement
//TODO passer sur un etat triple : ENABLE DISABLED SUSPENDED
public class ImmutableJmsTrigger
        extends ImmutablePackageAware<ImmutableJmsTrigger, MutableJmsTrigger, StringId, ActivableState>
        implements ReadableJmsTrigger {

    private final String name;

    public ImmutableJmsTrigger(ReadableJmsTrigger source) {
        super(source);
        name = source.getName();
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitComponent(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public ImmutableJmsTrigger toImmutable() {
        return this;
    }

    @Override
    public MutableJmsTrigger toMutable() {
        return new MutableJmsTrigger(this);
    }

    public static class MutableJmsTrigger
            extends MutablePackageAware<ImmutableJmsTrigger, MutableJmsTrigger, StringId, ActivableState>
            implements WritableJmsTrigger {
        @NotNull
        private String name;

        public MutableJmsTrigger(ReadableJmsTrigger source) {
            super(source);
            setName(source.getName());
            setType(ComponentType.JMS_TRIGGER);
        }

        public MutableJmsTrigger() {
            setType(ComponentType.JMS_TRIGGER);
        }

        @Override
        public ImmutableJmsTrigger toImmutable() {
            return new ImmutableJmsTrigger(this);
        }

        @Override
        public MutableJmsTrigger toMutable() {
            return new MutableJmsTrigger(this);
        }

        @Override
        public void accept(ConfigurationVisitor visitor) {
            visitor.visitComponent(toImmutable());
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
