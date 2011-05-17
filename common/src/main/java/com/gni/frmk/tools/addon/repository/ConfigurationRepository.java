package com.gni.frmk.tools.addon.repository;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 10:05
 *
 * @author: e03229
 */
public interface ConfigurationRepository {
    Set<ConfigurationId> getConfigurationList();

    void saveConfiguration(Configuration cnf);

    Configuration loadConfiguration(ConfigurationId id);

    boolean deleteConfiguration(ConfigurationId id);
}
