package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
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
public class JmsTrigger extends PackageAware<StringId, ActivableState> {

    @NotNull
    @XmlElement
    private final String name;

    public JmsTrigger(JmsTriggerBuilder builder) {
        super(builder);
        name = builder.name;
    }

    private JmsTrigger() {
        super();
        name = null;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public static JmsTriggerBuilder builder() {
        return new JmsTriggerBuilder();
    }

    public static class JmsTriggerBuilder extends PackageAware.Builder<JmsTriggerBuilder, JmsTrigger, StringId, ActivableState> {
        @NotNull
        private String name;

        public JmsTriggerBuilder() {
            defineType(ComponentType.JMS_TRIGGER);
        }

        @Override
        public JmsTriggerBuilder self() {
            return this;
        }

        public JmsTriggerBuilder name(String value) {
            name = value;
            defineId(new StringId(name));
            return self();
        }

        @Override
        protected JmsTrigger buildObjectBeforeValidation() {
            return new JmsTrigger(this);
        }
    }

}
