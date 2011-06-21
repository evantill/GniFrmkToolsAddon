package com.gni.frmk.tools.addon.operation.handler.component.test;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.test.Component1Id;
import com.gni.frmk.tools.addon.model.component.test.Component1Type;
import com.gni.frmk.tools.addon.model.component.test.SimpleDetail;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 17:30
 *
 * @author: e03229
 */
public class GetComponent1DetailStrategy
        implements GetComponentDetailStrategy<Component1Type, Component1Id, SimpleDetail> {

    @Override
    public SimpleDetail getDetail(Component1Id componentId, InvokeContext context) throws ServiceException {
        return SimpleDetail.newInstance("description component tpye1 id : " + componentId.getValue());
    }

    @Override
    public Component1Type getComponentType() {
        return Component1Type.TYPE;
    }
}
