package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.api.TestConfigurationStrategy.Operation;
import com.gni.frmk.tools.addon.service.api.Strategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:30
 *
 * @author: e03229
 */
public interface TestConfigurationStrategy extends Strategy<TestConfigurationStrategy, Operation> {

    public static interface Operation {
        TestComponentVisitor getVisitor();

        TestConfigurationVisited getVisited();
    }

    TestConfigurationStrategy execute(Operation o);
}
