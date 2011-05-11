package com.gni.frmk.tools.addon.action.configuration;

import com.gni.frmk.tools.addon.model.Configuration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 15:19
 *
 * @author: e03229
 */
public class OpenPlateform extends ConfigurationAction<ConfigurationResult>{
    private final boolean full;

    public OpenPlateform(Configuration configuration, boolean full) {
        super(configuration);
        this.full = full;
    }

    public boolean isFull() {
        return full;
    }
}
