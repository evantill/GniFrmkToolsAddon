package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.AdapterId;
import com.gni.frmk.tools.addon.model.BaseComponent;
import com.gni.frmk.tools.addon.model.EnableState;
import com.gni.frmk.tools.addon.model.TypedComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 09/03/11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
public class AdapterConnection
        extends BaseComponent<AdapterId, EnableState, AdapterConnection.Detail> {

    @Override
    public void accept(TypedComponentVisitor visitor) {
        visitor.visitAdapterConnection(this);
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
