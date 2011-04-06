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
 * Time: 13:09
 *
 * @author: e03229
 */
@XmlRootElement
public class AdapterNotification extends AdapterTypeAware<StringId, ActivableState> {

    @NotNull
    @XmlElement
    private final String name;

    public AdapterNotification(AdapterNotificationBuilder builder) {
        super(builder);
        name = builder.name;
    }

    private AdapterNotification() {
        super();
        name=null;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public static AdapterNotificationBuilder builder() {
        return new AdapterNotificationBuilder();
    }

    public static class AdapterNotificationBuilder extends AdapterTypeAware.Builder<AdapterNotificationBuilder, AdapterNotification, StringId, ActivableState> {

        @NotNull
        protected String name;

        public AdapterNotificationBuilder() {
            defineType(ComponentType.ADAPTER_NOTIFICATION);
        }

        public AdapterNotificationBuilder name(String value) {
            name = value;
            defineId(new StringId(name));
            return self();
        }

        @Override
        public AdapterNotificationBuilder self() {
            return this;
        }

        @Override
        protected AdapterNotification buildObjectBeforeValidation() {
            return new AdapterNotification(this);
        }

        @Override
        public AdapterNotificationBuilder from(AdapterNotification source) {
            super.from(source);
            name = source.getName();
            return self();
        }
    }

}
