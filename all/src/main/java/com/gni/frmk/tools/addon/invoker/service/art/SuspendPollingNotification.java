package com.gni.frmk.tools.addon.invoker.service.art;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.NoOutput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterComponentId;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 15:25
 *
 * @author: e03229
 */
public class SuspendPollingNotification extends WmService<AdapterComponentId, NoOutput> {

    public SuspendPollingNotification() {
        super("pub.art.notification:suspendPollingNotification");
    }

    @Override
    protected IData prepareInput(AdapterComponentId input) throws ServiceInputException {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 input.getName()}
        });
    }

    @Override
    protected NoOutput prepareOutput(IData output) throws ServiceOutputException {
        return NoOutput.singleton;
    }
}
