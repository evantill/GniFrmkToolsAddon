package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection.AdapterConnectionDetail;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 09/03/11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
public class AdapterConnection
        extends BaseComponent<AdapterId, EnableState, AdapterConnectionDetail> {

    public static class AdapterConnectionDetail extends BaseComponent.AbstractDetail {
        private String packageName;

        public AdapterConnectionDetail(String packageName) {
            this.packageName = packageName;
        }

        public AdapterConnectionDetail() {
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

    }
}
