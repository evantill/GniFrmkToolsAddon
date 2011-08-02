package com.gni.frmk.tools.addon.tdd.impl.component.beta;

import com.gni.frmk.tools.addon.tdd.impl.component.BaseTestComponentType;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:23
 *
 * @author: e03229
 */
public class BetaComponentType
        extends BaseTestComponentType<BetaComponent, BetaComponentId, BetaComponentState> {

    public BetaComponentType() {
        super(BetaComponent.class, BetaComponentId.class, BetaComponentState.class);
    }
}
