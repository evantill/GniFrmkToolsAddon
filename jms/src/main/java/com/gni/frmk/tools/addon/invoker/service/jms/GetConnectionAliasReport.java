package com.gni.frmk.tools.addon.invoker.service.jms;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsAliasInfo;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 15:35
 *
 * @author: e03229
 */
public class GetConnectionAliasReport extends WmService<NoInput, ListValueOutput<JmsAliasInfo>> {

    public GetConnectionAliasReport() {
        super("wm.server.jms:getConnectionAliasReport");
    }

    @Override
    protected IData prepareInput(NoInput input) throws ServiceInputException {
        return EMPTY_IDATA;
    }

    @Override
    protected ListValueOutput<JmsAliasInfo> prepareOutput(IData output) throws ServiceOutputException {
        IData[] aliasDataList = extractIDataArray(checkDataExist(output), "aliasDataList", false, EMPTY_IDATA_ARRAY);
        List<JmsAliasInfo> values = Lists.newArrayList();
        for (IData aliasData : aliasDataList) {
            IData checkedAliasData = checkDataExist(aliasData);
            JmsAliasInfo info = JmsAliasInfo.builder()
                                            .aliasName(extractStringValue(checkedAliasData, "aliasName", true, null))
                                            .description(extractStringValue(checkedAliasData, "description", false, ""))
                                            .enabled(extractBooleanValue(checkedAliasData, "enabled", true, null))
                                            .connected(extractBooleanValue(checkedAliasData, "connected", true, null))
                                            .build();
            values.add(info);
        }
        return ListValueOutput.newInstance(values);
    }
}
