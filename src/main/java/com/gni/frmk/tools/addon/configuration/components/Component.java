package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 09/03/11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
public interface Component {

    ComponentId getId();

    List<ComponentDetail> getDetails();

    ComponentState getState();

    ComponentType getType();

    void accept(ComponentVisitor visitor);

    interface ComponentDetail<T extends ComponentDetail.Value> {

        String getKey();

        Value getValue();

        public interface Value {
            String asString();
        }
    }

    interface ComponentId {
        String asString();
    }


    interface ComponentState {
    }
}
