package com.gni.frmk.tools.addon.handler.configuration.repository;

import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;

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
    void saveConfiguration(ImmutableConfiguration cnf);
    ImmutableConfiguration loadConfiguration(String id);
}
