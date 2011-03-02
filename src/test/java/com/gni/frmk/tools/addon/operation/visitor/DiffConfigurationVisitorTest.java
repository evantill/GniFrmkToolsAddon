package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.Configuration;
import com.gni.frmk.tools.addon.operation.strategy.ParseStrategy;
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
        Configuration c1 = ConfigurationUtils.loadConfiguration(DiffConfigurationVisitorTest.class, "configuration1");
        Configuration c2 = ConfigurationUtils.loadConfiguration(DiffConfigurationVisitorTest.class, "configuration2");
        DiffConfigurationVisitor visitor = new DiffConfigurationVisitor(c1);
        ParseStrategy strategy = new ParseStrategy(visitor);
        strategy.execute(c2);
        Configuration delta = visitor.getDifference();
        System.out.println("delta = " + delta);
    }
}
