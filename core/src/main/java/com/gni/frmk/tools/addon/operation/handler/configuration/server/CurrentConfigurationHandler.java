package com.gni.frmk.tools.addon.operation.handler.configuration.server;

import com.gni.frmk.tools.addon.operation.action.configuration.server.CurrentConfiguration;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 11:32
 *
 * @author: e03229
 */
public class CurrentConfigurationHandler implements ActionHandler<CurrentConfiguration,SingleResult<Configuration>, InvokeContext> {

    @Override
    public Class<CurrentConfiguration> getActionType() {
        return CurrentConfiguration.class;
    }

    @Override
    public SingleResult<Configuration> execute(CurrentConfiguration action, InvokeContext context) throws ActionException {
        //TODO FIX IT
        return null;
    }
}
