package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.ComponentStateType;
import com.gni.frmk.tools.addon.model.component.test.Component1;
import com.gni.frmk.tools.addon.model.component.test.Component2;
import com.gni.frmk.tools.addon.model.component.test.ComponentResource;
import com.gni.frmk.tools.addon.model.component.test.ConfigurationComposantType1;
import com.gni.frmk.tools.addon.model.component.test.ConfigurationComposantType2;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class ConfigurationBuilder {

    private int index;
    private final Date now;

    private ComponentResource componentBuilder = new ComponentResource();

    public ConfigurationBuilder(int indexStart, Date now) {
        this.index = indexStart;
        this.now = now;
    }

    public Configuration createConfiguration() {
        Configuration cnf = new Configuration();
        cnf.setName("test");
        cnf.setId(new ConfigurationId("testId000", "Default"));
        cnf.setCreation(now);
        cnf.setModification(now);
        cnf.getComponentConfigurations().add(createComponent1());
        cnf.getComponentConfigurations().add(createComponent2());
        return cnf;
    }

    public ConfigurationComposantType1 createComponent1() {
        Component1 component = componentBuilder.createComponent1();
        ConfigurationComposantType1 conf = new ConfigurationComposantType1();
        conf.setComponent(component);
        conf.getStateConfigurations()
            .put(ComponentStateType.OPEN, componentBuilder.createComponent1OpenState());
        conf.getStateConfigurations()
            .put(ComponentStateType.CLOSE, componentBuilder.createComponent1CloseState());
        conf.getStateConfigurations().put(ComponentStateType.CURRENT, component.getCurrentState());
        return conf;
    }

    public ConfigurationComposantType2 createComponent2() {
        Component2 component = componentBuilder.createComponent2();
        ConfigurationComposantType2 conf = new ConfigurationComposantType2();
        conf.setComponent(component);
        conf.getStateConfigurations()
            .put(ComponentStateType.OPEN, componentBuilder.createComponent2OpenState());
        conf.getStateConfigurations()
            .put(ComponentStateType.CLOSE, componentBuilder.createComponent2CloseState());
        conf.getStateConfigurations().put(ComponentStateType.CURRENT, component.getCurrentState());
        return conf;
    }

    public Configuration newSimpleConfiguration() {
        Configuration cnf = new Configuration();
        cnf.setId(new ConfigurationId("configuration_test_simple", "Default"));
        cnf.setCreation(now);
        cnf.setModification(now);
        cnf.setName("configuration_test_simple");

        List<ComponentConfiguration> componentConfigurations = Lists.newArrayList();
        componentConfigurations.add(createComponent1());
        componentConfigurations.add(createComponent2());

        cnf.setComponentConfigurations(componentConfigurations);
        return cnf;
    }
}
