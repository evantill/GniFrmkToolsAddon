package com.gni.frmk.tools.addon.invoker.io.art;

import com.gni.frmk.tools.addon.invoker.api.ServiceInput;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 15:26
 *
 * @author: e03229
 */
public class AdapterComponentId implements ServiceInput {
    private final String adapterType;
    private final String name;

    private AdapterComponentId(String adapterType, String name) {
        this.adapterType = checkNotNull(adapterType);
        this.name = checkNotNull(name);
    }

    public String getAdapterType() {
        return adapterType;
    }

    public String getName() {
        return name;
    }

    public static AdapterComponentId newInstance(String adapterType, String name) {
        return new AdapterComponentId(adapterType, name);
    }
}
