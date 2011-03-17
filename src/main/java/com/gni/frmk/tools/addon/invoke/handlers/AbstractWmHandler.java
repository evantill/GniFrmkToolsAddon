package com.gni.frmk.tools.addon.invoke.handlers;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.invoke.ActionException;
import com.gni.frmk.tools.addon.invoke.Action;
import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.ActionPattern.Result;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.SimpleServiceRegistry;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 19:40
 *
 * @author: e03229
 */
public abstract class AbstractWmHandler {
    private final IntegrationServerUtil isUtil;
    private final SimpleServiceRegistry registry = new SimpleServiceRegistry();
    private final String packageName;
    private final InvokeContext context;

    protected AbstractWmHandler(String packageName, IntegrationServerUtil util, InvokeContext context) {
        isUtil = util;
        this.context = context;
        this.packageName = packageName;
    }

    protected <A extends Action<R>, R extends Result> void addHandler(ActionHandler<A, R, ? extends InvokeContext> invoker) {
        registry.addHandler(invoker);
    }

    protected <A extends Action<R>, R extends Result> R invoke(A action) throws ActionException {
        return registry.findHandler(action).execute(action, context);
    }

    public boolean isEnabled() {
        return isUtil.isPackageEnabled(packageName);
    }

}
