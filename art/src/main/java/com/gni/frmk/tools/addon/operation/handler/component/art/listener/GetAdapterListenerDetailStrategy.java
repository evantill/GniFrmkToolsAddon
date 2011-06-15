package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterListenerInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.art.ListAdapterListeners;
import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListenerType;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;
import com.google.common.base.Predicate;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetAdapterListenerDetailStrategy
        implements GetComponentDetailStrategy<AdapterListenerType, AdapterId, PackageDetail> {

    private final ListAdapterListeners listeners = new ListAdapterListeners();

    @Override
    public AdapterListenerType getComponentType() {
        return AdapterListenerType.TYPE;
    }

    @Override
    public PackageDetail getDetail(final AdapterId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        AdapterTypeInput input = AdapterTypeInput.newInstance(componentId.getAdapterType());
        ListValueOutput<AdapterListenerInfo> infos = listeners.invoke(input, serviceContext);
        AdapterListenerInfo info = infos.getSingleValue(new Predicate<AdapterListenerInfo>() {
            @Override
            public boolean apply(AdapterListenerInfo input) {
                return input != null &&
                       componentId.getName().equals(input.getNotificationNodeName());
            }
        });
        return PackageDetail.build(info.getPackageName());
    }

}
