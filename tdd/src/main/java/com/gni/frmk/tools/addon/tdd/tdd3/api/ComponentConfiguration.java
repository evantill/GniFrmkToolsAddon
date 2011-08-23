package com.gni.frmk.tools.addon.tdd.tdd3.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:28
 *
 * @author: e03229
 */
public interface ComponentConfiguration extends Iterable<ComponentConfiguration> {
    ComponentType getType();

    ComponentId getId();

    ComponentData getData();
}
