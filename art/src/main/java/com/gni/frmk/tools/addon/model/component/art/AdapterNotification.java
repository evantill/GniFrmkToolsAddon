package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification.AdapterNotificationDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:09
 *
 * @author: e03229
 */

public class AdapterNotification
        extends BaseComponent<AdapterId, ActivableState, AdapterNotificationDetail> {

    public static class AdapterNotificationDetail extends BaseComponent.AbstractDetail {
        private String packageName;

        public AdapterNotificationDetail(String packageName) {
            this.packageName = packageName;
        }

        public AdapterNotificationDetail() {
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

    }
}

