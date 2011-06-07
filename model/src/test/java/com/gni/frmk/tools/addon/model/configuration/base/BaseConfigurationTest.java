package com.gni.frmk.tools.addon.model.configuration.base;

import com.gni.frmk.tools.addon.model.component.test.*;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationInfo;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/06/11
 * Time: 14:30
 *
 * @author: e03229
 */
public class BaseConfigurationTest
        extends AbstractConfigurationTest<BaseConfiguration> {

    public BaseConfigurationTest() {
        super(BaseConfiguration.class);
    }

    @Override
    protected BaseConfiguration buildConfiguration() {
        return BaseConfiguration.builder()
                                .id(buildConfigurationId())
                                .info(buildConfigurationInfo())
                                .addAllComponentConfiguration(buildComponentConfigurations())
                                .validate()
                                .build();
    }

    private Collection<ComponentConfiguration<?, ?, ?, ?>> buildComponentConfigurations() {
        Collection<ComponentConfiguration<?, ?, ?, ?>> configurations = Lists.newArrayList();
        configurations.add(buildComponent1Configuration());
        configurations.add(buildComponent2Configuration());
        return configurations;
    }

    private BaseComponentConfiguration<Component1Type, Component1, Component1State> buildComponent1Configuration() {
        Component1 component = Component1.builder()
                                         .id(Component1Id.builder().value("component id 1").build())
                                         .state(Component1State.builder().enable(true).build())
                                         .detail(SimpleDetail.builder().description("decription composant 1").build())
                                         .build();
        return BaseComponentConfiguration.builder(Component1Type.TYPE)
                                         .component(component)
                                         .openState(Component1State.builder().enable(true).build())
                                         .closeState(Component1State.builder().enable(false).build())
                                         .presentOnIS(true)
                                         .build();
    }

    private BaseComponentConfiguration<Component2Type, Component2, Component2State> buildComponent2Configuration() {
        Component2 component = Component2.builder()
                                         .id(Component2Id.builder().value(2).build())
                                         .state(Component2State.builder().enable(true).active(true).build())
                                         .detail(SimpleDetail.builder().description("decription composant 2").build())
                                         .build();
        return BaseComponentConfiguration.builder(Component2Type.TYPE)
                                         .component(component)
                                         .openState(Component2State.builder().enable(true).active(true).build())
                                         .closeState(Component2State.builder().enable(false).active(false).build())
                                         .presentOnIS(true)
                                         .build();
    }

    private ConfigurationInfo<?> buildConfigurationInfo() {
        return BaseConfigurationInformation.builder()
                                           .name("configuration user friendly name")
                                           .creation(nowRule.now())
                                           .modification(nowRule.now())
                                           .validate().build();
    }

    private ConfigurationId<?> buildConfigurationId() {
        return BaseConfigurationId.builder()
                                  .packageName("configuration package name")
                                  .id("configuration id")
                                  .validate().build();
    }

    @Override
    @Test
    public void testToXml() throws JAXBException, IOException, SAXException {
        super.testToXml();
    }

    @Override
    @Test
    public void testFromXml() throws JAXBException, IOException, SAXException {
        super.testFromXml();
    }
}
