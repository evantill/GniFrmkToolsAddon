package com.gni.frmk.tools.addon.tdd.impl.component.integrationserver;

import com.gni.frmk.tools.addon.tdd.api.ComponentFactory.ComponentFactoryDelegate;
import com.gni.frmk.tools.addon.tdd.util.UnimplementedMethodException;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 15:39
 *
 * @author: e03229
 */
public class IntegrationServerFactory implements ComponentFactoryDelegate<IntegrationServerType, IntegrationServer, IntegrationServerId> {

    @Override
    public IntegrationServer createComponent(IntegrationServerId id) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public Class<IntegrationServerType> getCreatedComponentType() {
        return IntegrationServerType.class;
    }
}
