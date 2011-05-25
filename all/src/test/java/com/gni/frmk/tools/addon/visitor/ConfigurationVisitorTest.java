package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.api.custom.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTest;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTestRule;
import com.gni.frmk.tools.addon.util.FileResource;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:16
 *
 * @author: e03229
 */
public class ConfigurationVisitorTest {

    @Rule
    public ConfigurationTestRule utils = new ConfigurationTestRule();

    @Rule
    public FileResource expectedXml = new FileResource(ConfigurationTest.class, "ConfigurationTest.xml");

    @Test
    public void testVisitor() {
//        Configuration cnf = utils.getConfigurationBuilder().createConfiguration();
//        ConfigurationVisitor visitor = Mockito.mock(ConfigurationVisitor.class);
//
//        cnf.accept(visitor);
//
//        Mockito.verify(visitor).visitConfiguration(cnf);
//        for (ComponentConfiguration<?, ?> cc : cnf) {
//            Mockito.verify(visitor).visitComponentConfiguration(cc);
//            Mockito.verify(visitor).visitComponent(cc.getComponent());
//        }
//        Mockito.verifyZeroInteractions(visitor);
    }
}
