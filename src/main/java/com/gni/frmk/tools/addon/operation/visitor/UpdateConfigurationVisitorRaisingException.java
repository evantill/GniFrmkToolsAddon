package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.Configuration;

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