package com.gni.frmk.tools.addon.tdd.tdd3.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:05
 *
 * @author: e03229
 */
public interface Component<D> extends ComponentData<D>, ComponentStrategy {
    ComponentType getType();

    ComponentId getId();

    ComponentState getState();

}
