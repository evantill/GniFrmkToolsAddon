package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.api.TConfigurationStrategy.Operation;
import com.gni.frmk.tools.addon.service.api.Strategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:30
 *
 * @author: e03229
 */
public interface TConfigurationStrategy extends Strategy<TConfigurationStrategy, Operation> {

    public static interface Operation {
        TComponentVisitor getVisitor();

        TConfigurationVisited getVisited();
    }

    TConfigurationStrategy execute(Operation o);
}
