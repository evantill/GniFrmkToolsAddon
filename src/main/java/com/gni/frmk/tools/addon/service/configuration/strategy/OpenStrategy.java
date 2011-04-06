package com.gni.frmk.tools.addon.service.configuration.strategy;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationVisited;

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
