package com.gni.frmk.tools.addon.operation.handler.component.test;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.test.Component1Id;
import com.gni.frmk.tools.addon.model.component.test.Component1State;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 17:30
 *
 * @author: e03229
 */
public class GetComponent1StateStrategy
        implements GetComponentStateStrategy<Component1Type, Component1Id, Component1State> {

    @Override
    public Component1State getState(Component1Id componentId, InvokeContext context)
            throws ServiceException {
        return Component1State.builder().enable(true).validate().build();
    }

    @Override
    public Component1Type getComponentType() {
        return Component1Type.TYPE;
    }


}
