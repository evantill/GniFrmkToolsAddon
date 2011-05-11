package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.visitor.TypedComponentVisitor;
import com.gni.frmk.tools.addon.model.component.JmsAlias.Detail;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
public class JmsAlias
        extends BaseComponent<StringId, ConnectableState, Detail> {

    @Override
    public void accept(TypedComponentVisitor visitor) {
        visitor.visitJmsAlias(this);
    }

    public static class Detail extends BaseComponent.AbstractDetail {
        private String description;

        public Detail(String description) {
            this.description = description;
        }

        public Detail() {
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}

