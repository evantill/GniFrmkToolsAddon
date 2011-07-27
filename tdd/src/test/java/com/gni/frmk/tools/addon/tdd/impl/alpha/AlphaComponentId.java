package com.gni.frmk.tools.addon.tdd.impl.alpha;

import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:26
 *
 * @author: e03229
 */
public class AlphaComponentId implements ComponentId<AlphaComponentId> {

    @Override
    public int compareTo(AlphaComponentId o) {
        return ComparisonChain.start()
                              .compare(this.hashCode(), o.hashCode())
                              .result();
    }
}
