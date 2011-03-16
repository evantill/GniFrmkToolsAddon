package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionException;
import com.gni.frmk.tools.addon.invoke.wmroot.GetPackageListInvokerTest;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:11
 *
 * @author: e03229
 */
public class Recorder {

    public static void main(String[] args) throws ActionException {
        RemoteInvokeContext ctx = new RemoteInvokeContext("ar1tn232.groupe.generali.fr:7502", "evantill", "evantill", true);
        ctx.connect();
        try {
            new GetPackageListInvokerTest().record(ctx);
        } finally {
            ctx.disconnect();
        }
    }
}
