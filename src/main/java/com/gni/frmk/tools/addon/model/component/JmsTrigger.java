package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.BaseComponent;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.visitor.TypedComponentVisitor;
import com.gni.frmk.tools.addon.model.component.JmsTrigger.Detail;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
//TODO passer sur un etat triple : ENABLE DISABLED SUSPENDED
public class JmsTrigger extends BaseComponent<StringId, ActivableState, Detail> {

    @Override
    public void accept(TypedComponentVisitor visitor) {
        visitor.visitJmsTrigger(this);
    }

    public static class Detail extends BaseComponent.AbstractDetail {
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
