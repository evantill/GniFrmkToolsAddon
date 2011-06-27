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
public class RecordTestContext implements ServiceContext {

    private final ServiceContext decorated;
    private final RecordPipelineUtils utils;

    private RecordTestContext(Class<?> clazz, ServiceContext decorated,RecordPipelineUtilsStrategy strategy) {
        utils = new RecordPipelineUtils(clazz,strategy);
        this.decorated = decorated;
    }

    @Override
    public IData invoke(NSName serviceName, IData in) throws ServiceContextException {
        String id = utils.generateId(serviceName);
        IData output = decorated.invoke(serviceName, utils.recordInput(id, in));
        return utils.recordOutput(id, output);
    }

    @Override
    public void dispose() {
        decorated.dispose();
    }

    public static RecordTestContext newInstance(Class<?> testClass, ServiceContext decorated,RecordPipelineUtilsStrategy strategy) {
        return new RecordTestContext(testClass, decorated,strategy);
    }
}
