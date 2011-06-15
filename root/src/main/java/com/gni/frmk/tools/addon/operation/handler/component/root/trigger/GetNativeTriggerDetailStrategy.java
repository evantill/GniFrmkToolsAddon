package com.gni.frmk.tools.addon.operation.handler.component.root.trigger;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.service.root.GetTriggerReport;
import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerType;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetNativeTriggerDetailStrategy
        implements GetComponentDetailStrategy<NativeTriggerType, StringId, NoDetail> {

    private final GetTriggerReport triggers = new GetTriggerReport();

    @Override
    public NativeTriggerType getComponentType() {
        return NativeTriggerType.TYPE;
    }

    @Override
    public NoDetail getDetail(final StringId componentId, InvokeContext context) throws ServiceException {
        return NoDetail.newInstance();
    }
}
