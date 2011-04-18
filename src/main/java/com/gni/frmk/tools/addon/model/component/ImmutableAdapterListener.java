package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterListener.MutableAdapterListener;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:08
 *
 * @author: e03229
 */
@XmlRootElement
public class ImmutableAdapterListener
        extends ImmutableAdapterTypeAware<ImmutableAdapterListener, MutableAdapterListener, StringId, ActivableState>
        implements ReadableAdapterListener {

    private final String name;

    public ImmutableAdapterListener(ReadableAdapterListener source) {
        super(source);
        name = source.getName();
    }

    @Override
    public ImmutableAdapterListener toImmutable() {
        return this;
    }

    @Override
    public MutableAdapterListener toMutable() {
        return new MutableAdapterListener(this);
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitComponent(this);
    }

    public String getName() {
        return name;
    }

    public static class MutableAdapterListener
            extends MutableAdapterTypeAware<ImmutableAdapterListener, MutableAdapterListener, StringId, ActivableState>
            implements WritableAdapterListener {
        private String name;

        public MutableAdapterListener() {
            setType(ComponentType.ADAPTER_LISTENER);
        }

        public MutableAdapterListener(ReadableAdapterListener source) {
            super(source);
            setName(source.getName());
            setType(ComponentType.ADAPTER_LISTENER);
        }


        @Override
        public ImmutableAdapterListener toImmutable() {
            return new ImmutableAdapterListener(this);
        }

        @Override
        public MutableAdapterListener toMutable() {
            return new MutableAdapterListener(this);
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
