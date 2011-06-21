package com.gni.frmk.tools.addon.invoker.service.art;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterPollingNotificationInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

import static com.gni.frmk.tools.addon.invoker.io.art.AdapterPollingNotificationInfo.builder;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 13:52
 *
 * @author: e03229
 */
public class ListAdapterPollingNotifications extends WmService<AdapterTypeInput, ListValueOutput<AdapterPollingNotificationInfo>> {

     ListAdapterPollingNotifications() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    protected IData prepareInput(AdapterTypeInput input) throws ServiceInputException {
        return ArtServiceUtils.prepareListComponentsInput(input);
    }

    @Override
    protected ListValueOutput<AdapterPollingNotificationInfo> prepareOutput(IData output) throws ServiceOutputException {
        IData checkedOutput = checkDataExist(output);
        String adapterType = extractStringValue(checkedOutput, "adapterTypeName", true,null);
        IData[] notificationDataList = extractIDataArray(checkedOutput, "notificationDataList", true,null);
        List<AdapterPollingNotificationInfo> values = Lists.newArrayList();
        for (IData notificationData : notificationDataList) {
            IData checkedData = checkDataExist(notificationData);
            AdapterPollingNotificationInfo info = AdapterPollingNotificationInfo.builder()
                    .adapterType(adapterType)
                    .notificationNodeName(extractStringValue(checkedData, "notificationNodeName", true,null))
                    .packageName(extractStringValue(checkedData, "packageName", true,null))
                    .notificationState(extractStringValue(checkedData, "notificationEnabled", true,null))
                    .build();
            values.add(info);
        }
        return ListValueOutput.newInstance(values);
    }
}
