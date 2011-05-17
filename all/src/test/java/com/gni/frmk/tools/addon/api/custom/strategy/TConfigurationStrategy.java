package com.gni.frmk.tools.addon.api.custom.strategy;

import com.gni.frmk.tools.addon.api.strategy.Strategy;
import com.gni.frmk.tools.addon.api.custom.visitor.TComponentVisitor;
import com.gni.frmk.tools.addon.api.custom.strategy.TConfigurationStrategy.Operation;
import com.gni.frmk.tools.addon.api.custom.visitor.TConfigurationVisited;

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
