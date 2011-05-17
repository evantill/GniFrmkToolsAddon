package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener.AdapterListenerDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:08
 *
 * @author: e03229
 */
public class AdapterListener extends BaseComponent<AdapterId, ActivableState, AdapterListenerDetail> {

    public static class AdapterListenerDetail extends BaseComponent.AbstractDetail {
        private String packageName;

        public AdapterListenerDetail(String packageName) {
            this.packageName = packageName;
        }

        public AdapterListenerDetail() {
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

    }
}
