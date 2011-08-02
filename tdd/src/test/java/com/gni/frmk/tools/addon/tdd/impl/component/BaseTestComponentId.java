package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/08/11
 * Time: 17:51
 *
 * @author: e03229
 */
public abstract class BaseTestComponentId<I extends BaseTestComponentId<I>>
        implements ComponentId<I> {

    private int id;

    protected BaseTestComponentId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(I o) {
        return ComparisonChain.start()
                              .compare(this.getId(), o.getId())
                              .result();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                      .add("id", id)
                      .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseTestComponentId that = (BaseTestComponentId) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
