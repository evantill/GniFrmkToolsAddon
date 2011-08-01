package com.gni.frmk.tools.addon.tdd.impl.component.beta;

import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.impl.component.BaseTestComponentId;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:26
 *
 * @author: e03229
 */
public class BetaComponentId
        extends BaseTestComponentId<BetaComponentId>
        implements ComponentId<BetaComponentId> {

    private BetaComponentId(int id) {
        super(id);
    }

    public static BetaComponentId newInstance(int id) {
        return new BetaComponentId(id);
    }
}
