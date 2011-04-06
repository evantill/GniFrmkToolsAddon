package com.gni.frmk.tools.addon.oldies.visitor.api;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.visitor.api.ConfigurationVisitorRaisingException;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public interface UpdateConfigurationVisitorRaisingException extends ConfigurationVisitorRaisingException {
    Configuration getUpdatedConfiguration() throws ConfigurationVisitorException;
}
