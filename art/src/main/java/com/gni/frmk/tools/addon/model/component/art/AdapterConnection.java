package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 09/03/11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class AdapterConnection
        extends BaseComponent<AdapterConnection, AdapterConnectionType, AdapterId, EnableState, PackageDetail> {

    private AdapterConnection() {
        super(AdapterConnectionType.TYPE);
    }

    private AdapterConnection(Builder builder) {
        super(builder);
    }

    public static final Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponent.Builder<Builder, AdapterConnection, AdapterConnectionType, AdapterId, EnableState, PackageDetail> {

        public Builder() {
            super(AdapterConnectionType.newInstance());
        }

        @Override
        public AdapterConnection build() {
            return new AdapterConnection(this);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder validate() {
            return super.validate();
        }
    }
}
