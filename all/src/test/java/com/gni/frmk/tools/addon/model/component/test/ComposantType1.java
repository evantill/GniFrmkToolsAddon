package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.BaseComponent;
import com.gni.frmk.tools.addon.model.component.test.ComposantType1.Type1Id;
import com.gni.frmk.tools.addon.model.component.test.ComposantType1.Type1State;
import com.gni.frmk.tools.addon.model.configuration.BaseComponentConfiguration;
import com.google.common.collect.ComparisonChain;

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

    public static class Type1State extends BaseComponent.AbstractState<Type1State> {
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

        @Override
        public boolean isUnknown() {
            return false;
        }

        @Override
        public int compareTo(Type1State other) {
            return ComparisonChain.start()
                                  .compare(0, super.compareTo(other))
                                  .compare(isEnabled(), other.isEnabled())
                                  .result();
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
