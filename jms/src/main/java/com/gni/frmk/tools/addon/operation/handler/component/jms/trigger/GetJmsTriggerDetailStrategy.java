package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsTriggerInfo;
import com.gni.frmk.tools.addon.invoker.service.jms.GetJmsTriggerReport;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTriggerType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;
import com.google.common.base.Predicate;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetJmsTriggerDetailStrategy
        implements GetComponentDetailStrategy<JmsTriggerType, StringId, PackageDetail> {

    private final GetJmsTriggerReport triggers;

    @Inject
    public GetJmsTriggerDetailStrategy(GetJmsTriggerReport triggers) {
        this.triggers = triggers;
    }

    @Override
    public JmsTriggerType getComponentType() {
        return JmsTriggerType.TYPE;
    }

    @Override
    public PackageDetail getDetail(final StringId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        ListValueOutput<JmsTriggerInfo> infos = triggers.invoke(NoInput.singleton, serviceContext);
        JmsTriggerInfo info = infos.getSingleValue(new Predicate<JmsTriggerInfo>() {
            @Override
            public boolean apply(JmsTriggerInfo input) {
                return input != null && componentId.getValue().equals(input.getNodeName());
            }
        });
        return PackageDetail.build(info.getPackageName());
    }

}
