package com.gni.frmk.tools.addon.tdd.impl.component.integrationserver;

import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 15:31
 *
 * @author: e03229
 */
public class IntegrationServerState implements ComponentState<IntegrationServerState> {

    private final IntegrationServerType componentType;
    private final IntegrationServerId componentId;

    public IntegrationServerState(IntegrationServerType componentType, IntegrationServerId componentId) {
        this.componentType = componentType;
        this.componentId = componentId;
    }

    @Override
    public IntegrationServerType getComponentType() {
        return componentType;
    }

    @Override
    public IntegrationServerId getComponentId() {
        return componentId;
    }

    @Override
    public int compareTo(IntegrationServerState o) {
        return ComparisonChain.start()
                              .compare(getComponentType(), o.getComponentType())
                              .compare(getComponentId(), o.getComponentId())
                              .result();
    }
}
