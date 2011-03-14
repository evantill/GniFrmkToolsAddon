package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.Component.ComponentDetail;
import com.gni.frmk.tools.addon.configuration.components.Component.ComponentDetail.Value;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 13:48
 *
 * @author: e03229
 */
public class SimpleDetail implements ComponentDetail<Value> {
    private final String key;
    private final Value value;

    public SimpleDetail(String key, String value) {
        this.key = key;
        this.value = new Value(value);
    }

    public String getKey() {return key;}

    public Value getValue() {return value;}

    public static class Value implements ComponentDetail.Value {
        private final String value;

        public Value(String value) {this.value = value;}

        public String getValue() {return value;}

        public String asString() {return getValue();}
    }
}
