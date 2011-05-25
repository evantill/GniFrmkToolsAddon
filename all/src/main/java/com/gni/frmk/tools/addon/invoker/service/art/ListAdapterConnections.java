package com.gni.frmk.tools.addon.invoker.service.art;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterConnectionInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

import static com.gni.frmk.tools.addon.invoker.io.art.AdapterConnectionInfo.builder;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 11:05
 *
 * @author: e03229
 */
public class ListAdapterConnections
        extends WmService<AdapterTypeInput, ListValueOutput<AdapterConnectionInfo>> {

    public ListAdapterConnections() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    protected IData prepareInput(AdapterTypeInput input) throws ServiceInputException {
        return ArtServiceUtils.prepareListComponentsInput(input);
    }

    @Override
    protected ListValueOutput<AdapterConnectionInfo> prepareOutput(IData output) throws ServiceOutputException {
        IData checkedOutput = checkDataExist(output);
        IData[] connectionDataList = extractIDataArray(checkedOutput, "connectionDataList", true,EMPTY_IDATA_ARRAY);
        String adapterType = extractStringValue(checkedOutput, "adapterTypeName", true,null);
        List<AdapterConnectionInfo> values = Lists.newArrayList();
        for (IData connectionData : connectionDataList) {
            IData checkedData = checkDataExist(connectionData);
            AdapterConnectionInfo info = builder()
                    .adapterType(adapterType)
                    .alias(extractStringValue(checkedData, "connectionAlias", true,null))
                    .packageName(extractStringValue(checkedData, "packageName", true,null))
                    .connectionState(extractStringValue(checkedData, "connectionState", true,null))
                    .build();
            values.add(info);
        }
        return ListValueOutput.newInstance(values);
    }
}
