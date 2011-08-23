package com.gni.frmk.tools.addon.tdd.tdd3.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:05
 *
 * @author: e03229
 * <p/>
 * persist data (xml files,db....)
 */
public interface ComponentConfigurationRepository {
    ComponentConfiguration load(ComponentType type, ComponentId id);

    void save(ComponentConfiguration saved);
}
