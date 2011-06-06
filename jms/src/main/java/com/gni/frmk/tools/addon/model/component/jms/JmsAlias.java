package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
@XmlRootElement
public class JmsAlias
        extends BaseComponent<JmsAlias,JmsAliasType,StringId, ConnectableState, JmsAliasDetail> {

    private JmsAlias() {
        super(JmsAliasType.TYPE);
    }

    public JmsAlias(Builder builder) {
        super(builder);
    }

    public static Builder builder(){
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponent.Builder<Builder,JmsAlias,JmsAliasType,StringId,ConnectableState,JmsAliasDetail>{
        public Builder() {
            super(JmsAliasType.newInstance());
        }

        @Override
        public JmsAlias build() {
            return new JmsAlias(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}

