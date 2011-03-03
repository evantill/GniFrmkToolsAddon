package com.gni.frmk.tools.addon.data2;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 09:32
 * To change this template use File | Settings | File Templates.
 */

public interface Component extends Comparable<Component> {
    ComponentType getType();

    ComponentId getId();

    Set<ComponentDetail> getDetails();

    ComponentState getState();

    void accept(ComponentVisitor visitor) ;
}
