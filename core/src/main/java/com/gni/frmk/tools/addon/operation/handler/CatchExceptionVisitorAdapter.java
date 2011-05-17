package com.gni.frmk.tools.addon.operation.handler;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.api.visitor.Visitable;
import com.gni.frmk.tools.addon.api.visitor.Visitor;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeDispatcher;
import com.gni.frmk.tools.addon.operation.api.Result;
import com.google.common.collect.Lists;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.unmodifiableList;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 18:31
 *
 * @author: e03229
 */
public abstract class CatchExceptionVisitorAdapter<V extends Visitor<V, T>, T extends Visitable<V, T>>
        implements Visitor<V, T> {

    private final InvokeDispatcher dispatcher;
    private final Action<?> action;
    private final List<ActionException> errors = Lists.newArrayList();

    protected CatchExceptionVisitorAdapter(InvokeDispatcher dispatcher, Action<?> action) {
        this.dispatcher = dispatcher;
        this.action = action;
    }

    public void notifyError(Throwable error, T visited) {
        errors.add(new ActionException(action,error));
    }

    public List<ActionException> getErrors() {
        return unmodifiableList(errors);
    }

    public <A extends Action<R>, R extends Result> R dispatch(A action, T visited) {
        try {
            return dispatcher.execute(action);
        } catch (DispatchException e) {
            notifyError(e, visited);
            return null;
        }
    }
}
