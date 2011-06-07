package com.gni.frmk.tools.addon.visitor.oldies;

import com.gni.frmk.tools.addon.model.configuration.test.oldies.ConfigurationTest;
import com.gni.frmk.tools.addon.util.FileResource;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:16
 *
 * @author: e03229
 */
public class ConfigurationVisitorTest {

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
