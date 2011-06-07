package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.configuration.base.BaseComponentConfiguration;

import javax.xml.bind.annotation.XmlRootElement;

/**
* Created by IntelliJ IDEA.
* Date: 01/06/11
* Time: 11:32
*
* @author: e03229
*/
@XmlRootElement
public class ConfigurationComposantType1
        extends BaseComponentConfiguration<Component1Type,Component1, Component1State> {

    public ConfigurationComposantType1(Builder<Component1Type, Component1, Component1State> builder) {
        super(builder);
    }

    private ConfigurationComposantType1() {
    }
}
