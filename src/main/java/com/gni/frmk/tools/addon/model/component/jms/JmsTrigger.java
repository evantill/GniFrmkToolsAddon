package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.BaseComponent;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.visitor.TypedComponentVisitor;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger.JmsTriggerDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
//TODO passer sur un etat triple : ENABLE DISABLED SUSPENDED
public class JmsTrigger extends BaseComponent<StringId, ActivableState, JmsTriggerDetail> {

    @Override
    public void accept(TypedComponentVisitor visitor) {
        visitor.visitJmsTrigger(this);
    }

    public static class JmsTriggerDetail extends BaseComponent.AbstractDetail {
        private String packageName;

        public JmsTriggerDetail(String packageName) {
            this.packageName = packageName;
        }

        public JmsTriggerDetail() {
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

    }

}
