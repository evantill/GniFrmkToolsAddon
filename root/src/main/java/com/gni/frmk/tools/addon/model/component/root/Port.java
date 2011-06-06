package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
@XmlRootElement
public class Port
        extends BaseComponent<Port,PortType,PackageAndStringId, ActivableState, PortDetail> {

    private Port() {
        super(PortType.TYPE);
    }

    public Port(Builder builder) {
        super(builder);
    }

    public static   Builder builder(){
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponent.Builder<Builder,Port,PortType,PackageAndStringId,ActivableState,PortDetail>{
        public Builder() {
            super(PortType.newInstance());
        }

        @Override
        public Port build() {
            return new Port(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
