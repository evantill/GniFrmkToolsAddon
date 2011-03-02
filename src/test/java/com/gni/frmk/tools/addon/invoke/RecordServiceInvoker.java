package com.gni.frmk.tools.addon.invoke;

import com.wm.data.IData;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 25/10/10
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class RecordServiceInvoker extends RemoteServiceInvoker {

    private final PipelineTestUtils testUtils;
    private final Properties props = new Properties();

    public RecordServiceInvoker(RecordServiceInvokerBuilder builder) {
        super(builder);
        this.testUtils = builder.testUtils;
        saveProperties();
    }

    @Override
    protected IData invokeHandler(IData input) throws Exception {
        testUtils.saveServiceInput(getFullName(), input);
        IData output = super.invokeHandler(input);
        testUtils.saveServiceOutput(getFullName(), output);
        return output;
    }

    @Override
    public boolean exist() {
        boolean exist = super.exist();
        if (!props.contains("exist")) {
            props.setProperty("exist", Boolean.toString(exist));
            saveProperties();
        }
        return exist;
    }

    private void saveProperties() {
        try {
            testUtils.saveServiceProperties(getFullName(), props);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("can not save service %s properties", getFullName()), e);
        }
    }
}
