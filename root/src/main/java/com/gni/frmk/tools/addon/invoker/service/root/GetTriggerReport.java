package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInfo;
import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInfo.Builder;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 19:26
 *
 * @author: e03229
 */
public class GetTriggerReport extends WmService<NoInput, ListValueOutput<NativeTriggerInfo>> {

     GetTriggerReport() {
        super("wm.server.triggers:getTriggerReport");
    }

    @Override
    protected IData prepareInput(NoInput input) throws ServiceInputException {
        return EMPTY_IDATA;
    }

    @Override
    protected ListValueOutput<NativeTriggerInfo> prepareOutput(IData output) throws ServiceOutputException {
        List<NativeTriggerInfo> values = Lists.newArrayList();
        IData[] triggers = extractIDataArray(checkDataExist(output), "triggers", false, EMPTY_IDATA_ARRAY);
        for (IData trigger : triggers) {
            IData checkedTrigger = checkDataExist(trigger);
            Builder builder = NativeTriggerInfo.builder();
            builder.name(extractStringValue(checkedTrigger, "name", true, null));
            IData checkedProperties = checkDataExist(extractIData(checkedTrigger, "properties", true, null));
            builder.executeEnabled(extractBooleanValue(checkedProperties, "executeEnabled", true, null));
            IData checkedRetreivalStatus = checkDataExist(extractIData(checkedTrigger, "retrievalStatus", true, null));
            builder.retrieval(NativeTriggerUtils.parseState(checkedRetreivalStatus));
            IData checkedProcessingStatus = checkDataExist(extractIData(checkedTrigger, "processingStatus", true, null));
            builder.processing(NativeTriggerUtils.parseState(checkedProcessingStatus));
            values.add(builder.build());
        }
        return ListValueOutput.newInstance(values);
    }
}
