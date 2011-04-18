package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.api.ImmutableComponent;
import com.gni.frmk.tools.addon.model.component.ImmutableNativeTrigger.MutableNativeTrigger;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
@XmlRootElement
public class ImmutableNativeTrigger
        extends ImmutableComponent<ImmutableNativeTrigger, MutableNativeTrigger, StringId, NativeTriggerState>
        implements ReadableNativeTrigger {
    private final String name;

    public ImmutableNativeTrigger(ReadableNativeTrigger source) {
        super(source);
        name = source.getName();
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public ImmutableNativeTrigger toImmutable() {
        return this;
    }

    @Override
    public MutableNativeTrigger toMutable() {
        return new MutableNativeTrigger(this);
    }

    public String getName() {
        return name;
    }

    public static class MutableNativeTrigger
            extends MutableComponent<ImmutableNativeTrigger, MutableNativeTrigger, StringId, NativeTriggerState>
            implements WritableNativeTrigger {

        private String name;

        public MutableNativeTrigger() {
            setType(ComponentType.NATIVE_TRIGGER);
        }

        public MutableNativeTrigger(ReadableNativeTrigger source) {
            super(source);
            setName(source.getName());
            setType(ComponentType.NATIVE_TRIGGER);
        }

        @Override
        public void setName(String value) {
            setComponentId(new StringId(value));
            name = value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public ImmutableNativeTrigger toImmutable() {
            return new ImmutableNativeTrigger(this);
        }

        @Override
        public MutableNativeTrigger toMutable() {
            return new MutableNativeTrigger(this);
        }

        @Override
        public void accept(ConfigurationVisitor visitor) {
            visitor.visitComponent(toImmutable());
        }
    }

}
