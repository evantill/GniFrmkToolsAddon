package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisitor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
@XmlRootElement
public class JmsAlias extends AbstractComponent<StringId, ConnectableState> {

    @NotNull
    @XmlElement
    private final String name;

    @NotNull
    @XmlElement
    private final String description;

    public JmsAlias(JmsAliasBuilder builder) {
        super(builder);
        name = builder.name;
        description = builder.description;
    }

    private JmsAlias(){
        super();
        name=null;
        description=null;
    }
    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static JmsAliasBuilder builder() {
        return new JmsAliasBuilder();
    }

    public static class JmsAliasBuilder extends AbstractComponent.Builder<JmsAliasBuilder, JmsAlias, StringId, ConnectableState> {

        @NotNull
        private String name;
        private String description;

        public JmsAliasBuilder() {
            defineType(ComponentType.JMS_ALIAS);
        }

        @Override
        public JmsAliasBuilder self() {
            return this;
        }

        public JmsAliasBuilder name(String value) {
            name = value;
            defineId(new StringId(name));
            return self();
        }

        public JmsAliasBuilder description(String value) {
            description = value;
            return self();
        }

        @Override
        protected JmsAlias buildObjectBeforeValidation() {
            return new JmsAlias(this);
        }
    }
}

