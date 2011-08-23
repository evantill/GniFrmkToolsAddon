package com.gni.frmk.tools.addon.tdd.tdd3.api;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:08
 *
 * @author: e03229
 */
public interface ComponentFactory extends ComponentDataFactory, ComponentStrategyFactory {
    List<ComponentType> getComponentTypes();

    List<ComponentId> getComponentIdsByType(ComponentType type);

    Component getComponent(ComponentType type, ComponentId id);

    List<Component> getComponentsByType(ComponentType type);
}
