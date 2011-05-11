package com.gni.frmk.tools.addon.result;

import com.gni.frmk.tools.addon.model.Component.Detail;

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
