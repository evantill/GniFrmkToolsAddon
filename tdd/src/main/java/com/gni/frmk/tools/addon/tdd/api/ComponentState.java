package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 15:04
 *
 * @author: e03229
 */
public interface ComponentState<T extends ComponentState<T>> extends Comparable<T> {

    ComponentType<?,?,?> getComponentType();
    ComponentId<?> getComponentId();
}
