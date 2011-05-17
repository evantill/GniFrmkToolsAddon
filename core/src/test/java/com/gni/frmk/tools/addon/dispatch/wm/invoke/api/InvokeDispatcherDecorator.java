package com.gni.frmk.tools.addon.dispatch.wm.invoke.api;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.api.Result;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 15:51
 *
 * @author: e03229
 */
public class InvokeDispatcherDecorator implements Dispatcher {
    private final InvokeDispatcher decorated;
    private final List<Class<?>> executedActions = Lists.newArrayList();

    public InvokeDispatcherDecorator(InvokeDispatcher decorated) {
        this.decorated = decorated;
    }

    @Override
    public <A extends Action<R>, R extends Result> R execute(A action) throws DispatchException {
        executedActions.add(action.getClass());
        return decorated.execute(action);
    }

    public List<Class<?>> getExecutedActions() {
        return Collections.unmodifiableList(executedActions);
    }
}
