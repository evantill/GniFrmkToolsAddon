package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;
import com.gni.frmk.tools.addon.model.component.Component.Id;
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
        <A extends ChangeComponentState<I, S>,
                I extends Id,
                S extends AbstractState<S>,
                CTX extends ExecutionContext>
        implements ActionHandler<A, NoResult, CTX> {

    protected final List<Action<?>> NO_ACTION = singleAction(new Action<NoResult>() {});

    protected List<Action<?>> singleAction(Action<?> action) {
        return Collections.<Action<?>>singletonList(action);
    }

    @Override
    public NoResult execute(A action, CTX context) throws ActionException {
        S oldState = action.getOldComponentState();
        S newState = action.getNewComponentState();
        if (!isSameState(oldState, newState)) {
            if (newState.isUnknown() || oldState.isUnknown()) {
                throw new ActionException(action, "both states must be known to change component state");
            }
            List<Action<?>> delegates = defineActions(oldState, newState, action.getComponentId());
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
