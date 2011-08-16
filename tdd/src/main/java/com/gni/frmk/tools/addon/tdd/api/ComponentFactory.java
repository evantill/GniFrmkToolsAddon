package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 16:50
 *
 * @author: e03229
 */
public interface ComponentFactory {
    Component createComponent(ComponentType type, ComponentId id);
}
