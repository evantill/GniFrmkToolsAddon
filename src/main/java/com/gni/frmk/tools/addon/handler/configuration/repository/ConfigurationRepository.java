package com.gni.frmk.tools.addon.handler.configuration.repository;

import com.gni.frmk.tools.addon.model.configuration.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 10:05
 *
 * @author: e03229
 */
public interface ConfigurationRepository {
    Set<String> getConfigurationList();
    void saveConfiguration(Configuration cnf);
    Configuration loadConfiguration(String id);
}
