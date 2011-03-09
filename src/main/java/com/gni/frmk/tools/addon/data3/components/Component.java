package com.gni.frmk.tools.addon.data3.components;

import com.gni.frmk.tools.addon.data3.visitors.ComponentVisitor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/03/11
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public interface Component {
    ComponentType getType();

    ComponentId getId();

    ComponentState getState();

    List<? extends ComponentInformation> getInformations();

    void accept(ComponentVisitor visitor);

}
