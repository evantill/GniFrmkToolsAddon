package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.component.test.ComposantType2.Type2Id;
import com.gni.frmk.tools.addon.model.component.test.ComposantType2.Type2State;
import com.gni.frmk.tools.addon.model.configuration.BaseComponentConfiguration;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:16
 *
 * @author: e03229
 */
public class ComposantType2
        extends BaseComponent<Type2Id, Type2State, SimpleDetail> {

    public static class ConfigurationComposantType2
            extends BaseComponentConfiguration<ComposantType2, Type2State> {

    }

    public static class Type2Id extends BaseComponent.AbstractId {
        private Integer value;

        public Type2Id() {
        }

        public Type2Id(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public static class Type2State extends BaseComponent.AbstractState {
        private boolean active;
        private boolean enabled;

        public Type2State(boolean active, boolean enabled) {
            super(true);
            this.active = active;
            this.enabled = enabled;
        }

        public Type2State() {
            super(false);
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
