package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.Configuration;
import com.gni.frmk.tools.addon.model.ConfigurationTest;
import com.gni.frmk.tools.addon.model.FileResource;
import com.gni.frmk.tools.addon.model.ConfigurationUtils;
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
    public ConfigurationUtils utils = new ConfigurationUtils("2010-05-03T20:01:59+01:00");

    @Rule
    public FileResource expectedXml = new FileResource(ConfigurationTest.class, "ConfigurationTest.xml");

    @Test
    public void testVisitor() {
        Configuration cnf = utils.createConfiguration();
        ConfigurationVisitor visitor = Mockito.mock(ConfigurationVisitor.class);

        cnf.accept(visitor);

        Mockito.verify(visitor).visitConfiguration(cnf);
//        for (ComponentConfiguration<?, ?> cc : cnf) {
//            Mockito.verify(visitor).visitComponentConfiguration(cc);
//            Mockito.verify(visitor).visitComponent(cc.getComponent());
//        }
//        Mockito.verifyZeroInteractions(visitor);
    }
}
