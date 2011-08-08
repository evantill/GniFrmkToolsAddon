package com.gni.frmk.tools.addon.tdd.impl.component.test.alpha2;

import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/08/11
 * Time: 17:56
 *
 * @author: e03229
 */
public class Alpha2ComponentId implements ComponentId<Alpha2ComponentId> {
    private final int id;

    private Alpha2ComponentId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(final Alpha2ComponentId other) {
        return ComparisonChain.start().compare(getId(), other.getId()).result();
    }

    public static Alpha2ComponentId newInstance(int id) {
        return new Alpha2ComponentId(id);
    }
}
