package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
@XmlRootElement
public class NativeTrigger extends PackageAware<StringId, NativeTriggerState> {

    @NotNull
    @XmlElement
    private final String name;

    public NativeTrigger(NativeTriggerBuilder builder) {
        super(builder);
        name = builder.name;
    }

    private NativeTrigger() {
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

    public static NativeTriggerBuilder builder() {
        return new NativeTriggerBuilder();
    }

    public static class NativeTriggerBuilder extends PackageAware.Builder<NativeTriggerBuilder, NativeTrigger, StringId, NativeTriggerState> {

        @NotNull
        private String name;

        public NativeTriggerBuilder() {
            defineType(ComponentType.NATIVE_TRIGGER);
        }

        @Override
        public NativeTriggerBuilder self() {
            return this;
        }

        public NativeTriggerBuilder name(String value) {
            name = checkNotNull(value);
            defineId(new StringId(name));
            return self();
        }

        @Override
        protected NativeTrigger buildObjectBeforeValidation() {
            return new NativeTrigger(this);
        }
    }

}
