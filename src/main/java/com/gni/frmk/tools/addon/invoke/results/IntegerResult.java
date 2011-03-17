package com.gni.frmk.tools.addon.invoke.results;

import com.gni.frmk.tools.addon.invoke.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 15:47
 *
 * @author: e03229
 */
public class IntegerResult implements Result {
    private final Integer value;

    public IntegerResult(Integer value) {this.value = value;}

    public Integer getValue() {
        return value;
    }
}
