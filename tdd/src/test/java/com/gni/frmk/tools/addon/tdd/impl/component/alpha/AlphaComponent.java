package com.gni.frmk.tools.addon.tdd.impl.component.alpha;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.impl.component.BaseTestComponent;
import com.google.common.base.Objects;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:24
 *
 * @author: e03229
 */
public class AlphaComponent
        extends BaseTestComponent<AlphaComponent, AlphaComponentType, AlphaComponentId, AlphaComponentState>
        implements Component<AlphaComponent, AlphaComponentState> {


    private AlphaComponent(AlphaComponentType type, AlphaComponentId id, AlphaComponentState state, boolean opened) {
        super(type, id, state, opened);
    }

    @Override
    public int getOpenOrder() {
        return 10+getId().getId();
    }

    public static final AlphaComponent newInstance(int id, boolean opened) {
        AlphaComponentId componentId = AlphaComponentId.newInstance(id);
        AlphaComponentType type = new AlphaComponentType();
        AlphaComponentState state = AlphaComponentState.newInstance(type,componentId);
        return new AlphaComponent(type, componentId, state, opened);
    }
}
