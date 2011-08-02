package com.gni.frmk.tools.addon.tdd.impl.component.beta;

import com.gni.frmk.tools.addon.tdd.impl.component.BaseTestComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:25
 *
 * @author: e03229
 */
public class BetaComponentState
        extends BaseTestComponentState<BetaComponentType, BetaComponentId, BetaComponentState> {
    private BetaComponentState(BetaComponentType componentType, BetaComponentId componentId) {
        super(componentType, componentId);
    }

     public static final BetaComponentState newInstance(BetaComponentType type, BetaComponentId id) {
        return new BetaComponentState(type, id);
    }
}
