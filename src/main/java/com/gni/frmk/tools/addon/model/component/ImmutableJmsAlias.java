package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.api.ImmutableComponent;
import com.gni.frmk.tools.addon.model.component.ImmutableJmsAlias.MutableJmsAlias;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
@XmlRootElement
public class ImmutableJmsAlias
        extends ImmutableComponent<ImmutableJmsAlias, MutableJmsAlias, StringId, ConnectableState>
        implements ReadableJmsAlias {

    private final String name;
    private final String description;

    public ImmutableJmsAlias(ReadableJmsAlias source) {
        super(source);
        name = source.getName();
        description = source.getDescription();
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitComponent(this);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public ImmutableJmsAlias toImmutable() {
        return this;
    }

    @Override
    public MutableJmsAlias toMutable() {
        return new MutableJmsAlias(this);
    }

    public static class MutableJmsAlias
            extends MutableComponent<ImmutableJmsAlias, MutableJmsAlias, StringId, ConnectableState>
            implements WritableJmsAlias {

        private String name;
        private String description;

        public MutableJmsAlias() {
            setType(ComponentType.JMS_ALIAS);
        }

        public MutableJmsAlias(ReadableJmsAlias source) {
            setName(source.getName());
            setDescription(source.getDescription());
            setType(ComponentType.JMS_ALIAS);
        }

        @Override
        public ImmutableJmsAlias toImmutable() {
            return new ImmutableJmsAlias(this);
        }

        @Override
        public MutableJmsAlias toMutable() {
            return new MutableJmsAlias(this);
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

