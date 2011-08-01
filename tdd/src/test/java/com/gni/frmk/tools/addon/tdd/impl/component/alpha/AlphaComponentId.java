package com.gni.frmk.tools.addon.tdd.impl.component.alpha;

import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.impl.component.BaseTestComponentId;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:26
 *
 * @author: e03229
 */
public class AlphaComponentId
        extends BaseTestComponentId<AlphaComponentId>
        implements ComponentId<AlphaComponentId> {


    private AlphaComponentId(int id) {
        super(id);
    }

    public static AlphaComponentId newInstance(int id) {
        return new AlphaComponentId(id);
    }
}
