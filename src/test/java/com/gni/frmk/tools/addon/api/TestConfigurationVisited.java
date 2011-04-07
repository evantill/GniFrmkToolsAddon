package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.service.api.Visitable;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:25
 *
 * @author: e03229
 */
public interface TestConfigurationVisited extends Visitable<TestConfigurationVisitor, TestConfigurationVisited> {

    TestComposant1 getComposant1();
    TestComposant2 getComposant2();

}
