package com.gni.frmk.tools.addon.oldies.visitor;

import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationTestRule;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 04/11/10
 * Time: 09:50
 * To change this template use File | Settings | File Templates.
 */
public class DiffConfigurationVisitorTest {

    @Test
    public void testDiffConfigurationSimple() throws Exception {
        ImmutableConfiguration c1 = ConfigurationTestRule.loadConfiguration(DiffConfigurationVisitorTest.class, "configuration1");
        ImmutableConfiguration c2 = ConfigurationTestRule.loadConfiguration(DiffConfigurationVisitorTest.class, "configuration2");
//        TODO_DiffConfigurationVisitor_TODO visitor = new TODO_DiffConfigurationVisitor_TODO(c1);
//        ParseStrategy strategy = new ParseStrategy(visitor);
//        strategy.execute(c2);
//        Configuration delta = visitor.getDifference();
//        System.out.println("delta = " + delta);
    }
}
