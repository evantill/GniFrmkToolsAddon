package com.gni.frmk.tools.addon.invoker.io.art;

import com.gni.frmk.tools.addon.invoker.api.ServiceInput;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 12:00
 *
 * @author: e03229
 */
public final class AdapterTypeInput implements ServiceInput, Comparable<AdapterTypeInput> {
    private final String adapterType;

    private AdapterTypeInput(String adapterType) {
        this.adapterType = checkNotNull(adapterType);
    }

    public String getAdapterType() {
        return adapterType;
    }

    public static final AdapterTypeInput newInstance(String adapterType) {
        return new AdapterTypeInput(adapterType);
    }

    @Override
    public int compareTo(AdapterTypeInput o) {
        return ComparisonChain.start()
                              .compare(getAdapterType(), o.getAdapterType())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdapterTypeInput that = (AdapterTypeInput) o;

        return Objects.equal(getAdapterType(), that.getAdapterType());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getAdapterType());
    }
}
