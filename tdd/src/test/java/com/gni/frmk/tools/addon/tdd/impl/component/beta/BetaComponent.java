package com.gni.frmk.tools.addon.tdd.impl.component.beta;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.impl.component.BaseTestComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:24
 *
 * @author: e03229
 */
public class BetaComponent
        extends BaseTestComponent<BetaComponent, BetaComponentType, BetaComponentId, BetaComponentState>
        implements Component<BetaComponent, BetaComponentState> {

    private BetaComponent(BetaComponentType type, BetaComponentId id, BetaComponentState state, boolean opened) {
        super(type, id, state, opened);
    }

    @Override
    public int getOpenOrder() {
        return 20 + getId().getId();
    }

    public static final BetaComponent newInstance(int id, boolean opened) {
        BetaComponentId componentId = BetaComponentId.newInstance(id);
        BetaComponentType type = new BetaComponentType();
         BetaComponentState state = BetaComponentState.newInstance(type,componentId);
        return new BetaComponent(type, componentId, state, opened);
    }
}
