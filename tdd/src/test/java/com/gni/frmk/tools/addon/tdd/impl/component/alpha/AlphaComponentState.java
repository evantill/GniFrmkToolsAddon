package com.gni.frmk.tools.addon.tdd.impl.component.alpha;

import com.gni.frmk.tools.addon.tdd.impl.component.BaseTestComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:25
 *
 * @author: e03229
 */
public class AlphaComponentState
        extends BaseTestComponentState<AlphaComponentType, AlphaComponentId, AlphaComponentState> {

    private AlphaComponentState(AlphaComponentType componentType, AlphaComponentId componentId) {
        super(componentType, componentId);
    }

    public static final AlphaComponentState newInstance(AlphaComponentType type, AlphaComponentId id) {
        return new AlphaComponentState(type, id);
    }
}
