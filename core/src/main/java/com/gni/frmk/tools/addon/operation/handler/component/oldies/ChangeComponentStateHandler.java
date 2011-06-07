package com.gni.frmk.tools.addon.operation.handler.component.oldies;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;
import com.gni.frmk.tools.addon.operation.api.*;
import com.gni.frmk.tools.addon.operation.result.NoResult;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 14:07
 *
 * @author: e03229
 */
public abstract class ChangeComponentStateHandler
        <A extends ChangeComponentState<T, I, S>,
                T extends ComponentType<T, ?, I, S, ?>,
                I extends ComponentId<I>,
                S extends BaseComponentState<S>,
                CTX extends ExecutionContext>
        implements ActionHandler<A, NoResult, CTX> {

    protected final List<Action<?>> NO_ACTION = singleAction(new Action<NoResult>() {
    });

    protected List<Action<?>> singleAction(Action<?> action) {
        return Collections.<Action<?>>singletonList(action);
    }

    @Override
    public NoResult execute(A action, CTX context) throws ActionException {
        S oldState = action.getOldComponentState();
        S newState = action.getNewComponentState();
        if (!isSameState(oldState, newState)) {
            if (newState.unknown() || oldState.unknown()) {
                throw new ActionException(action, "both states must be known to change component state");
            }
            List<Action<?>> delegates = defineActions(oldState, newState, action.getId());
            for (Action<?> delegate : delegates) {
                delegateTo(action, delegate, context);
            }
        }
        return NoResult.newInstance();
    }

    protected <R extends Result> R delegateTo(A action, Action<R> delegate, CTX context)
            throws ActionException {
        try {
            return context.getDispatcher().execute(delegate);
        } catch (DispatchException e) {
            throw new ActionException(action, e);
        }
    }

    protected abstract List<Action<?>> defineActions(S oldState, S newState, I id);

    private boolean isSameState(S oldState, S newState) {
        return oldState.compareTo(newState) == 0;
    }
}
