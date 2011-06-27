package com.gni.frmk.tools.addon.invoker.context;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceContextException;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 12:23
 *
 * @author: e03229
 */
public class ReplayTestContext implements ServiceContext {

    private final RecordPipelineUtils utils;

    private ReplayTestContext(Class<?> clazz,RecordPipelineUtilsStrategy strategy) {
        utils = new RecordPipelineUtils(clazz,strategy);
    }

    @Override
    public IData invoke(NSName serviceName, IData in) throws ServiceContextException {
        String id = utils.generateId(serviceName);
        return utils.replayIData("out", id);
    }

    @Override
    public void dispose() {
        //noop
    }

    public static ReplayTestContext newInstance(Class<?> testClass,RecordPipelineUtilsStrategy strategy) {
        return new ReplayTestContext(testClass,strategy);
    }
}
