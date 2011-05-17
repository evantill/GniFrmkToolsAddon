package com.gni.frmk.tools.addon.operation.result;

import com.gni.frmk.tools.addon.model.component.Component.State;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:22
 *
 * @author: e03229
 */
public class ComponentStateResult<S extends State> extends SingleResult<S> {

    public ComponentStateResult(S value) {
        super(value);
    }
}
