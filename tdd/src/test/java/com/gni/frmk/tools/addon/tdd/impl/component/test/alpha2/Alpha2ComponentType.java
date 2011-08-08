package com.gni.frmk.tools.addon.tdd.impl.component.test.alpha2;

import com.gni.frmk.tools.addon.tdd.api.ComponentType;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/08/11
 * Time: 17:53
 *
 * @author: e03229
 */
public class Alpha2ComponentType implements ComponentType<Alpha2ComponentType> {

    private static Alpha2ComponentType singleton = new Alpha2ComponentType();

    private Alpha2ComponentType() {
    }

    @Override
    public int compareTo(final Alpha2ComponentType other) {
        return ComparisonChain.start().compare(hashCode(), other.hashCode()).result();
    }

    public static final Alpha2ComponentType newInstance() {
        return singleton;
    }
}
