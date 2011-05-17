package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.component.ConnectableState;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
public class JmsAlias
        extends BaseComponent<StringId, ConnectableState, JmsAliasDetail> {

    public static class JmsAliasDetail extends BaseComponent.AbstractDetail {
        private String description;

        public JmsAliasDetail(String description) {
            this.description = description;
        }

        public JmsAliasDetail() {
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}

