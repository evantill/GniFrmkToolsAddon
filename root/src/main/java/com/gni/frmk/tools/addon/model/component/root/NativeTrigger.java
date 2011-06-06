package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.NoDetail;
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
public class NativeTrigger
        extends BaseComponent<NativeTrigger, NativeTriggerType, StringId, NativeTriggerState, NoDetail> {

    private NativeTrigger() {
        super(NativeTriggerType.TYPE);
    }

    public NativeTrigger(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponent.Builder<Builder, NativeTrigger, NativeTriggerType, StringId, NativeTriggerState, NoDetail> {

        public Builder() {
            super(NativeTriggerType.newInstance());
        }

        @Override
        public NativeTrigger build() {
            return new NativeTrigger(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
