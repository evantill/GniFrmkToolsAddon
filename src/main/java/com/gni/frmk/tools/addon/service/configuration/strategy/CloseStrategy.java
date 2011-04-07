package com.gni.frmk.tools.addon.service.configuration.strategy;

import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:32
 *
 * @author: e03229
 */
public class CloseStrategy extends InputOutputStrategy {

    @Override
    public ConfigurationProcessingStrategy execute(Operation o) {
        processInput(o);
        waitServicesEnd();
        processOutput(o);
        return this;
    }

    protected void waitServicesEnd() {
        //TODO implementer
        throw new UnsupportedOperationException("waitServicesEnd not implemented");
    }

}
