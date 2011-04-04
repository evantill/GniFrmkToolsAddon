package com.gni.frmk.tools.addon.model.component.detail;

import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentDetail;
import com.gni.frmk.tools.addon.model.api.ComponentDetail;
import com.gni.frmk.tools.addon.model.api.ComponentDetail.Value;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 13:48
 *
 * @author: e03229
 */
@XmlRootElement
public class SimpleDetail extends AbstractComponentDetail<Value> implements ComponentDetail<Value> {
    @XmlElement
    private final String key;
    @XmlElement
    private final Value value;

    public SimpleDetail(String key, String value) {
        this.key = key;
        this.value = new Value(value);
    }

    private SimpleDetail() {
        key = null;
        value = null;
    }

    public String getKey() {return key;}

    public Value getValue() {return value;}

    @XmlRootElement
    public static final class Value implements ComponentDetail.Value {
        @XmlElement
        private final String value;

        private Value() {
            value = null;
        }

        public Value(String value) {this.value = value;}

        public String getValue() {return value;}

        public String asString() {return getValue();}
    }
}
