package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.ActivableState;
import com.gni.frmk.tools.addon.model.BaseComponent;
import com.gni.frmk.tools.addon.model.PackageAndStringId;
import com.gni.frmk.tools.addon.model.TypedComponentVisitor;
import com.gni.frmk.tools.addon.model.component.Port.Detail;

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
        extends BaseComponent<PackageAndStringId, ActivableState, Detail> {

    @Override
    public void accept(TypedComponentVisitor visitor) {
        visitor.visitPort(this);
    }

    public static class Detail extends AbstractDetail {
        private boolean primary;

        public Detail() {
        }

        public Detail(boolean primary) {
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
