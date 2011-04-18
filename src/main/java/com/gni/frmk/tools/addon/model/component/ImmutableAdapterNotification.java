package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterNotification.MutableAdapterNotification;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:09
 *
 * @author: e03229
 */
@XmlRootElement
public class ImmutableAdapterNotification
        extends ImmutableAdapterTypeAware<ImmutableAdapterNotification, MutableAdapterNotification, StringId, ActivableState>
        implements ReadableAdapterNotification {

    private final String name;

    public ImmutableAdapterNotification(ReadableAdapterNotification source) {
        super(source);
        name = source.getName();
    }

    @Override
    public ImmutableAdapterNotification toImmutable() {
        return this;
    }

    @Override
    public MutableAdapterNotification toMutable() {
        return new MutableAdapterNotification(this);
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitComponent(this);
    }

    public String getName() {
        return name;
    }

    public static class MutableAdapterNotification
            extends MutableAdapterTypeAware<ImmutableAdapterNotification, MutableAdapterNotification, StringId, ActivableState>
            implements WritableAdapterNotification {
        private String name;

        public MutableAdapterNotification() {
            setType(ComponentType.ADAPTER_NOTIFICATION);
        }

        public MutableAdapterNotification(ReadableAdapterNotification source) {
            super(source);
            setName(source.getName());
            setType(ComponentType.ADAPTER_NOTIFICATION);
        }


        @Override
        public ImmutableAdapterNotification toImmutable() {
            return new ImmutableAdapterNotification(this);
        }

        @Override
        public MutableAdapterNotification toMutable() {
            return new MutableAdapterNotification(this);
        }

        @Override
        public void accept(ConfigurationVisitor visitor) {
            visitor.visitComponent(toImmutable());
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            setComponentId(new StringId(name));
            this.name = name;
        }
    }

}
