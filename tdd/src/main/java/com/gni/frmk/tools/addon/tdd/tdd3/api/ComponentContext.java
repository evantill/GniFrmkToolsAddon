package com.gni.frmk.tools.addon.tdd.tdd3.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:08
 *
 * @author: e03229
 */
public interface ComponentContext {
    ComponentStrategyRegistry getComponentStrategyRegistry();

    ComponentConfigurationRepository getComponentDataRepository();

    ComponentRegistry getComponentRegistry();
}