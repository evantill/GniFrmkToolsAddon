package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.ActivableState;
import com.gni.frmk.tools.addon.model.AdapterId;
import com.gni.frmk.tools.addon.model.BaseComponent;
import com.gni.frmk.tools.addon.model.TypedComponentVisitor;
import com.gni.frmk.tools.addon.model.component.AdapterConnection.Detail;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:09
 *
 * @author: e03229
 */

public class AdapterNotification
        extends BaseComponent<AdapterId, ActivableState, Detail> {

    @Override
    public void accept(TypedComponentVisitor visitor) {
        visitor.visitAdapterNotification(this);
    }

    public static class Detail extends AbstractDetail {
        private String packageName;

        public Detail(String packageName) {
            this.packageName = packageName;
        }

        public Detail() {
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

    }
}
