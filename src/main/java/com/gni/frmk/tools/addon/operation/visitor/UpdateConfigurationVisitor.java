package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 19:35
 *
 * @author: e03229
 */
public interface UpdateConfigurationVisitor extends ConfigurationVisitorRaisingException {
    Configuration getUpdatedConfiguration()  throws ConfigurationVisitorException;
}