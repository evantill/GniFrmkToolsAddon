package com.gni.frmk.tools.addon.model.api;

import com.gni.frmk.tools.addon.visitor.api.ComponentVisitor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 09/03/11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
public interface Component<I extends ComponentId, S extends ComponentState> {

    I getComponentId();

    List<ComponentDetail> getDetails();

    ComponentType getType();

    S getState();

    void setState(S state);

    void accept(ComponentVisitor visitor);

}