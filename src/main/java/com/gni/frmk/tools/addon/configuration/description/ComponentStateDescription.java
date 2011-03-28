package com.gni.frmk.tools.addon.configuration.description;

import com.gni.frmk.tools.addon.configuration.BuilderWithValidation;
import com.gni.frmk.tools.addon.configuration.ComponentConfiguration.ComponentStateContext;
import com.gni.frmk.tools.addon.configuration.components.ComponentState;
import com.gni.frmk.tools.addon.configuration.components.ComponentStateType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import java.util.Map;
import java.util.Map.Entry;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 10:00
 *
 * @author: e03229
 */
@XmlRootElement(name = "state")
@XmlAccessorType(XmlAccessType.NONE)
public class ComponentStateDescription {
    public static enum ContextDescription {
        OPEN, CLOSE, CURRENT;

        public static final ContextDescription fromContext(ComponentStateContext context) {
            return valueOf(context.name());
        }

        public final ContextDescription toContext() {
            return ContextDescription.valueOf(this.name());
        }
    }

    @XmlAttribute
    private final ContextDescription context;

    @XmlAttribute
    private final ComponentStateType type;

    @XmlValue
    private final String representation;

    public ComponentStateDescription(Builder builder) {
        type=builder.type;
        context = builder.context;
        representation = builder.representation;
    }

    public ComponentStateType getType() {
        return type;
    }

    public ContextDescription getContext() {
        return context;
    }

    public String getRepresentation() {
        return representation;
    }

    /**
     * Empty constructor used by jaxb.
     */
    private ComponentStateDescription() {
        context = null;
        representation = null;
        type=null;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder implements BuilderWithValidation<Builder, ComponentStateDescription> {
        private static final String CONTEXT_REQUIRED = "component type required";
        private static final String REPRESENTATION_REQUIRED = "component id required";

        private String representation;
        private ContextDescription context;
        private ComponentStateType type;

        public Builder fromComponentState(ComponentState value) {
            type=value.getType();
            //TODO broken need repair URGENT
            representation = "builder.substring(1);";
            return this;
        }

        public Builder fromDescriptionState(Class<? extends ComponentState> type, String value) {
            representation = checkNotNull(value);
            return this;
        }

        public Builder inContext(ComponentStateContext value) {
            context = ContextDescription.fromContext(checkNotNull(value));
            return this;
        }

        public Builder inContext(ContextDescription value) {
            context = checkNotNull(value);
            return this;
        }

        public ComponentStateDescription build() {
            return new ComponentStateDescription(this);
        }

        @Override
        public ComponentStateDescription buildAndValidate() throws BuildException, ValidationException {
            return validate().build();
        }

        @Override
        public Builder validate() throws ValidationException {
            try {
                checkNotNull(context, CONTEXT_REQUIRED);
                checkNotNull(representation, REPRESENTATION_REQUIRED);
                return this;
            } catch (NullPointerException npex) {
                throw new ValidationException(npex);
            }
        }
    }
}
