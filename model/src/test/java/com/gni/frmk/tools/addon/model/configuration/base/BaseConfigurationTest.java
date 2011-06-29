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
import java.util.Set;

import static com.gni.frmk.tools.addon.model.component.ComponentStateType.CLOSE;
import static com.gni.frmk.tools.addon.model.component.ComponentStateType.OPEN;
import static org.fest.assertions.Assertions.assertThat;

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

    private Collection<ComponentConfiguration<?, ?, ?, ?, ?>> buildComponentConfigurations() {
        Collection<ComponentConfiguration<?, ?, ?, ?, ?>> configurations = Lists.newArrayList();
        configurations.add(buildComponent1Configuration());
        configurations.add(buildComponent2Configuration());
        return configurations;
    }

    private BaseComponentConfiguration<Component1Type, Component1, Component1Id, Component1State> buildComponent1Configuration() {
        Component1 component = Component1.builder()
                                         .id(Component1Id.builder().value("component id 1").build())
                                         .state(Component1State.builder().enable(true).build())
                                         .detail(SimpleDetail.builder().description("decription composant 1").build())
                                         .build();
        return BaseComponentConfiguration.builder(Component1Type.TYPE).fromComponent(component).build();
    }

    private BaseComponentConfiguration<Component2Type, Component2, Component2Id, Component2State> buildComponent2Configuration() {
        Component2 component = Component2.builder()
                                         .id(Component2Id.builder().value(2).build())
                                         .state(Component2State.builder().enable(true).active(true).build())
                                         .detail(SimpleDetail.builder().description("decription composant 2").build())
                                         .build();
        return BaseComponentConfiguration.builder(Component2Type.TYPE).fromComponent(component).build();
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


    @Test
    public void testGetComponentConfigurationsByType() throws JAXBException, IOException, SAXException {
        BaseConfiguration conf = buildConfiguration();
        ComponentConfiguration<?, ?, ?, ?, ?> expectedComponentConfiguration = null;
        Component1 expectedComponent = null;
        for (ComponentConfiguration<?, ?, ?, ?, ?> componentConfiguration : conf.getComponentConfigurations()) {
            if (componentConfiguration.getComponentType() instanceof Component1Type) {
                expectedComponentConfiguration = componentConfiguration;
                //noinspection UnusedAssignment
                expectedComponent = (Component1) componentConfiguration.getComponent();
            }
        }
        assertThat(expectedComponentConfiguration).isNotNull();
        assertThat(expectedComponent).isNotNull();

        Set<ComponentConfiguration<?, ?, ?, ?, ?>> components = conf.getComponentConfigurationsByType(Component1Type.TYPE);
        assertThat(components).containsOnly(expectedComponentConfiguration);
        assertThat(expectedComponent).isEqualTo(components.iterator().next().getComponent());
    }
}
