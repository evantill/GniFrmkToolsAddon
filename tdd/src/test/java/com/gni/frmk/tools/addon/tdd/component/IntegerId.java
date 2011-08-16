package com.gni.frmk.tools.addon.tdd.component;

import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 14:35
 *
 * @author: e03229
 */
public class IntegerId implements ComponentId<IntegerId> {
    private final int value;

    public IntegerId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(IntegerId o) {
        return ComparisonChain.start().compare(value, o.value).result();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("IntegerId");
        sb.append("{value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
