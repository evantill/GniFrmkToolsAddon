package com.gni.frmk.tools.addon.operation.result;

import com.gni.frmk.tools.addon.operation.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:32
 *
 * @author: e03229
 */
public class NoResult
        extends AbstractResult
        implements Result {

    //private static final NoResult SINGLETON = new NoResult();

    private NoResult() {
    }

    public static NoResult newInstance() {
        return new NoResult();
    }
}
