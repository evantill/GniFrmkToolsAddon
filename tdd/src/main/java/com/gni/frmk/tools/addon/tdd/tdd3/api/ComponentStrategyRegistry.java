package com.gni.frmk.tools.addon.tdd.tdd3.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:05
 *
 * @author: e03229
 *
 * permettra de definir un referentiel des strategies definies par les plugins
 */
public interface ComponentStrategyRegistry {
    void register(ComponentStrategyFactory factory);

    ComponentStrategy findStrategy(ComponentType type);
}
