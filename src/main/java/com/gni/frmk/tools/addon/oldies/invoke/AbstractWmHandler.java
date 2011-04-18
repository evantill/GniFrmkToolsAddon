package com.gni.frmk.tools.addon.oldies.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.api.action.Result;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeServiceRegistry;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 19:40
 *
 * @author: e03229
 */
public abstract class AbstractWmHandler {
    private final IntegrationServerUtil isUtil;
    private final InvokeServiceRegistry registry = new InvokeServiceRegistry();
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

    protected <A extends Action<R>, R extends Result> R invoke(A action) throws DispatchException {
        return registry.findHandler(action).execute(action, context);
    }

    public boolean isEnabled() {
        return isUtil.isPackageEnabled(packageName);
    }

}
