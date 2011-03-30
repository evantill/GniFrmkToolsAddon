package com.gni.frmk.tools.addon.configuration.component;

import com.gni.frmk.tools.addon.configuration.component.AbstractComponent.AbstractComponentId;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:56
 *
 * @author: e03229
 */
@XmlRootElement
public class StringId extends AbstractComponentId implements ComponentId {

    protected StringId() {
    }

    public StringId(String value) {
        super(value);
    }

    public String getValue() {
        return getRawValue();
    }

    @Override
    public String asString() {
        return getRawValue();
    }
}
