package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.BaseComponent;
import com.gni.frmk.tools.addon.model.BaseComponentConfiguration;
import com.gni.frmk.tools.addon.model.component.ComposantType1.Type1State;
import com.gni.frmk.tools.addon.model.component.ComposantType1.Type1Id;
import com.gni.frmk.tools.addon.model.component.detail.SimpleDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:16
 *
 * @author: e03229
 */
public class ComposantType1
        extends BaseComponent<Type1Id, Type1State, SimpleDetail> {

    public static class ConfigurationComposantType1
            extends BaseComponentConfiguration<ComposantType1, Type1State> {

    }

    public static class Type1State extends BaseComponent.AbstractState {
        private boolean enabled;

        public Type1State() {
            super(false);
        }

        public Type1State(boolean enabled) {
            super(true);
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static class Type1Id extends BaseComponent.AbstractId {
        private String value;

        public Type1Id() {
        }

        public Type1Id(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
