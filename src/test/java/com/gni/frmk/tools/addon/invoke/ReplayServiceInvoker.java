package com.gni.frmk.tools.addon.invoke;

import com.wm.data.IData;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 27/10/10
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
public class ReplayServiceInvoker extends ServiceInvoker {

    private final PipelineTestUtils testUtils;
    private final Properties props = new Properties();

    public ReplayServiceInvoker(ReplayServiceInvokerBuilder builder) {
        super(builder);
        this.testUtils = builder.testUtils;
        loadProperties();
    }

    @Override
    public boolean exist() {
        boolean exist = true;
        if (props.contains("exist")) {
            exist = Boolean.parseBoolean(props.getProperty("exist"));
        }
        return exist;
    }

    @Override
    protected IData invokeHandler(IData input) throws Exception {
        return testUtils.loadServiceOutput(getFullName());
    }

    private void loadProperties() {
        try {
            props.putAll(testUtils.loadServiceProperties(getFullName()));
        } catch (IOException e) {
            throw new IllegalStateException(String.format("can not load service %s properties", getFullName()), e);
        }
    }
}
