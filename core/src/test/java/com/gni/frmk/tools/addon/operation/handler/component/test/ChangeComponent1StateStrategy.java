package com.gni.frmk.tools.addon.operation.handler.component.test;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.test.Component1Id;
import com.gni.frmk.tools.addon.model.component.test.Component1State;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 17:30
 *
 * @author: e03229
 */
public class ChangeComponent1StateStrategy
        implements ChangeComponentStateStrategy<Component1Type, Component1Id, Component1State> {
    @Override
    public Component1State changeState(Component1Id componentId, Component1State oldState, Component1State newState, InvokeContext context)
            throws ServiceException {
        return newState;
    }

    @Override
    public Component1Type getComponentType() {
        return Component1Type.TYPE;
    }
}
