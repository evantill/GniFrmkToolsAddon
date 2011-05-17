package com.gni.frmk.tools.addon.operation.action.configuration;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 15:58
 *
 * @author: e03229
 */
public class AplyConfiguration extends ConfigurationAction<ConfigurationResult>{

      /**
     * Le changement de statut des triggers natifs est le suivant :
     * <code>
     * <ul>
     * <li>Enable/Permanent -> Enable/Permanent</li>
     * <li>Disable/Permanent -> Disable/Permanent</li>
     * <li>Enable/Temporary -> Enable/Temporary</li>
     * <li>Disable/Temporary -> Disable/Permanent</li>
     * </ul>
     * </code>
     * @param     configuration
     */
      //TODO implementer apply configuration action
    protected AplyConfiguration(
            Configuration configuration) {
        super(configuration);
    }
}
