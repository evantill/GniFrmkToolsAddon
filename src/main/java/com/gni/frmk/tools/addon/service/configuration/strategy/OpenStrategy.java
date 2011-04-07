package com.gni.frmk.tools.addon.service.configuration.strategy;

import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:09
 *
 * @author: e03229
 */
public class OpenStrategy extends InputOutputStrategy {

    @Override
    public ConfigurationProcessingStrategy execute(Operation o) {
        processOutput(o);

        processInput(o);


        return this;
    }



}
