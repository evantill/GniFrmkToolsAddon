package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:08
 *
 * @author: e03229
 */
@XmlRootElement
public class AdapterListener
        extends BaseComponent<AdapterListener, AdapterListenerType, AdapterId, ActivableState, PackageDetail> {

    private AdapterListener() {
        super(AdapterListenerType.TYPE);
    }

    public AdapterListener(Builder builder) {
        super(builder);
    }

    public static Builder builder(){
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponent.Builder<Builder,AdapterListener,AdapterListenerType,AdapterId,ActivableState,PackageDetail>{

        public Builder() {
            super(AdapterListenerType.newInstance());
        }

        @Override
        public AdapterListener build() {
            return new AdapterListener(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
