package com.gni.frmk.tools.addon.invoker.service.jms;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsTriggerInfo;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsTriggerInfo.Builder;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 16:42
 *
 * @author: e03229
 */
public class GetJmsTriggerReport extends WmService<NoInput, ListValueOutput<JmsTriggerInfo>> {

     GetJmsTriggerReport() {
        super("wm.server.jms:getTriggerReport");
    }

    @Override
    protected IData prepareInput(NoInput input) throws ServiceInputException {
        return EMPTY_IDATA;
    }

    @Override
    protected ListValueOutput<JmsTriggerInfo> prepareOutput(IData output) throws ServiceOutputException {
        IData[] triggerDataList = extractIDataArray(checkDataExist(output), "triggerDataList", false, EMPTY_IDATA_ARRAY);
        List<JmsTriggerInfo> values = Lists.newArrayList();
        for (IData triggerData : triggerDataList) {
            IData checkedData = checkDataExist(triggerData);
            Builder builder = JmsTriggerInfo.builder()
                                            .nodeName(extractStringValue(checkedData, "node_nsName", true, null))
                                            .packageName(extractStringValue(checkedData, "node_pkg", true, null));
            IData checkedTriggerData = checkDataExist(extractIData(checkedData, "trigger", true, null));
            builder.enabled(extractBooleanValue(checkedTriggerData, "enabled", true, null))
                   .running(extractBooleanValue(checkedTriggerData, "running", true, null))
                   .state(extractIntegerValue(checkedTriggerData, "state", true, null));
            values.add(builder.build());
        }
        return ListValueOutput.newInstance(values);
    }


}
