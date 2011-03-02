package com.gni.frmk.tools.addon.data.adapter;

import com.gni.frmk.tools.addon.data.component.Component;
import com.gni.frmk.tools.addon.data.component.ComponentBuilder;
import com.gni.frmk.tools.addon.data.component.ComponentInfo;
import com.gni.frmk.tools.addon.data.component.ComponentState;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class AdapterBuilder<C extends Component<F, S>, F extends ComponentInfo, S extends ComponentState>
        extends ComponentBuilder<C, F, S> {

    protected final String adapterType;

    public AdapterBuilder(String adapterType) {
        this.adapterType = adapterType;
    }

}
