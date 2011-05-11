package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.BaseComponent;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.visitor.TypedComponentVisitor;
import com.gni.frmk.tools.addon.model.component.root.Port.PortDetail;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
@XmlRootElement
public class Port
        extends BaseComponent<PackageAndStringId, ActivableState, PortDetail> {

    @Override
    public void accept(TypedComponentVisitor visitor) {
        visitor.visitPort(this);
    }

    public static class PortDetail extends BaseComponent.AbstractDetail {
        private boolean primary;

        public PortDetail() {
        }

        public PortDetail(boolean primary) {
            this.primary = primary;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }
    }


}
