package com.gni.frmk.tools.addon.invoker.service.art;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.wm.data.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:05
 *
 * @author: e03229
 */
public class RetrieveAdapterTypes extends WmService<NoInput, ListValueOutput<String>> {

    public RetrieveAdapterTypes() {
        super("wm.art.admin:retrieveAdapterTypesList");
    }

    @Override
    protected IData prepareInput(NoInput input) throws ServiceInputException {
        return EMPTY_IDATA;
    }

    @Override
    protected ListValueOutput<String> prepareOutput(IData output) throws ServiceOutputException {
        IData[] types = extractIDataArray(checkDataExist(output), "adapterTypes", true,null);
        List<String> result = newArrayList();
        for (IData type : types) {
            String adapterType = extractStringValue(checkDataExist(type), "adapterName", true,null);
            result.add(adapterType);
        }
        return ListValueOutput.newInstance(result);
    }
}
