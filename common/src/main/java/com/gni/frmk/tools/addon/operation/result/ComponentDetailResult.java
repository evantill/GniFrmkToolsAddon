package com.gni.frmk.tools.addon.operation.result;

import com.gni.frmk.tools.addon.model.component.Component.Detail;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:57
 *
 * @author: e03229
 */
public class ComponentDetailResult<D extends Detail> extends SingleResult<D> {

    public ComponentDetailResult(D value) {
        super(value);
    }
}
