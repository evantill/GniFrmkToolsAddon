package com.gni.frmk.tools.addon.operation.handler.configuration.server;

import com.gni.frmk.tools.addon.operation.action.configuration.server.CurrentConfiguration;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

import javax.enterprise.util.TypeLiteral;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 11:32
 *
 * @author: e03229
 */
public class CurrentConfigurationHandler implements ActionHandler<CurrentConfiguration,SingleResult<Configuration>, InvokeContext> {

    private static final TypeLiteral<CurrentConfiguration> TYPE_LITERAL = new TypeLiteral<CurrentConfiguration>() {};

    @Override
    public TypeLiteral<CurrentConfiguration> getActionType() {
        return TYPE_LITERAL;
    }

    @Override
    public SingleResult<Configuration> execute(CurrentConfiguration action, InvokeContext context) throws ActionException {
        //TODO FIX IT
        return null;
    }
}
