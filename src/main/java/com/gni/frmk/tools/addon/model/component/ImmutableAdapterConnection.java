package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterConnection.MutableAdapterConnection;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.EnableState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 09/03/11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class ImmutableAdapterConnection
        extends ImmutableAdapterTypeAware<ImmutableAdapterConnection, MutableAdapterConnection, StringId, EnableState>
        implements ReadableAdapterConnection {

    private final String alias;

    public ImmutableAdapterConnection(ReadableAdapterConnection source) {
        super(source);
        alias = source.getAlias();
    }

    @Override
    public void accept(ConfigurationVisitor visitor) {
        visitor.visitComponent(this);
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public ImmutableAdapterConnection toImmutable() {
        return this;
    }

    @Override
    public MutableAdapterConnection toMutable() {
        return new MutableAdapterConnection(this);
    }

    public static class MutableAdapterConnection
            extends MutableAdapterTypeAware<ImmutableAdapterConnection, MutableAdapterConnection, StringId, EnableState>
            implements WritableAdapterConnection {

        protected String alias;

        public MutableAdapterConnection(ReadableAdapterConnection source) {
            super(source);
            setAlias(source.getAlias());
            setType(ComponentType.ADAPTER_CONNECTION);
        }

        public MutableAdapterConnection() {
            setType(ComponentType.ADAPTER_CONNECTION);
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            setComponentId(new StringId(alias));
            this.alias = alias;
        }

        @Override
        public ImmutableAdapterConnection toImmutable() {
            return new ImmutableAdapterConnection(this);
        }

        @Override
        public MutableAdapterConnection toMutable() {
            return new MutableAdapterConnection(this);
        }

        @Override
        public void accept(ConfigurationVisitor visitor) {
            visitor.visitComponent(toImmutable());
        }
    }

}
