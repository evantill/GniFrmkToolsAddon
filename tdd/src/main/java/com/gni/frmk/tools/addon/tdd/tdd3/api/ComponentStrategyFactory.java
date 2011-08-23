package com.gni.frmk.tools.addon.tdd.tdd3.api;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:05
 *
 * @author: e03229
 */
public interface ComponentStrategyFactory {
    List<ComponentType> getComponentTypes();

    ComponentStrategy getStrategy(ComponentType type, ComponentId id);
}
