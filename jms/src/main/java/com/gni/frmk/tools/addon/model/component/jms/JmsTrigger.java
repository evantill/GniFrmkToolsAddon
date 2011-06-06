package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
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
//TODO passer sur un etat triple : ENABLE DISABLED SUSPENDED
@XmlRootElement
public class JmsTrigger
        extends BaseComponent<JmsTrigger, JmsTriggerType, StringId, ActivableState, PackageDetail> {

    private JmsTrigger() {
        super(JmsTriggerType.TYPE);
    }

    public JmsTrigger(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponent.Builder<Builder, JmsTrigger, JmsTriggerType, StringId, ActivableState, PackageDetail> {
        public Builder() {
            super(JmsTriggerType.newInstance());
        }

        @Override
        public JmsTrigger build() {
            return new JmsTrigger(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
