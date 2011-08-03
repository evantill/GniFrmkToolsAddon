package com.gni.frmk.tools.addon.tdd.impl.component.test.alpha;

import com.gni.frmk.tools.addon.tdd.impl.component.test.base.BaseTestComponentType;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:23
 *
 * @author: e03229
 */
public class AlphaComponentType
        extends BaseTestComponentType<AlphaComponent, AlphaComponentId, AlphaComponentState> {

    public AlphaComponentType() {
        super(AlphaComponent.class, AlphaComponentId.class, AlphaComponentState.class);
    }
}
