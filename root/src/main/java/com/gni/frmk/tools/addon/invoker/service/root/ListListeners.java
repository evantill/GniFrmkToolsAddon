package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.PortInfo;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 17:35
 *
 * @author: e03229
 */
public class ListListeners extends WmService<NoInput, ListValueOutput<PortInfo>> {

     ListListeners() {
        super("wm.server.ports:listListeners");
    }

    @Override
    protected IData prepareInput(NoInput input) throws ServiceInputException {
        return EMPTY_IDATA;
    }

    @Override
    protected ListValueOutput<PortInfo> prepareOutput(IData output) throws ServiceOutputException {
        List<PortInfo> values = Lists.newArrayList();
        IData[] listeners = extractIDataArray(checkDataExist(output), "listeners", false, EMPTY_IDATA_ARRAY);
        for (IData listener : listeners) {
            IData chackedListener = checkDataExist(listener);
            PortInfo info = PortInfo.builder()
                                    .port(extractStringValue(chackedListener, "port", true, null))
                                    .packageName(extractStringValue(chackedListener, "pkg", true, null))
                                    .protocol(extractStringValue(chackedListener, "protocol", true, null))
                                    .enabled(extractBooleanValue(chackedListener, "enabled", true, null))
                                    .key(extractStringValue(chackedListener, "key", true, null))
                                    .suspended(extractBooleanValue(chackedListener, "suspended", false, FALSE))
                                    .status(extractStringValue(chackedListener, "status", true, null))
                                    .listening(extractBooleanValue(chackedListener, "listening", true, null))
                                    .primary(extractBooleanValue(chackedListener, "primary", false, FALSE))
                                    .build();
            values.add(info);
        }
        return ListValueOutput.newInstance(values);
    }
}
