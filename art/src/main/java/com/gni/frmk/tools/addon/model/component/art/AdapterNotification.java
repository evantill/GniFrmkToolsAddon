package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:09
 *
 * @author: e03229
 */
@XmlRootElement
public class AdapterNotification
        extends BaseComponent<AdapterNotification, AdapterNotificationType, AdapterId, ActivableState, PackageDetail> {

    private AdapterNotification() {
        super(AdapterNotificationType.TYPE);
    }

    public AdapterNotification(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponent.Builder<Builder, AdapterNotification, AdapterNotificationType, AdapterId, ActivableState, PackageDetail> {

        public Builder() {
            super(AdapterNotificationType.newInstance());
        }

        @Override
        public AdapterNotification build() {
            return new AdapterNotification(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}

