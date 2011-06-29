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
public class ConfigurationComposantType2
        extends BaseComponentConfiguration<Component2Type, Component2, Component2Id, Component2State> {

    public ConfigurationComposantType2(Builder<Component2Type, Component2, Component2Id, Component2State> builder) {
        super(builder);
    }

    private ConfigurationComposantType2() {
    }
}
