package com.gni.frmk.tools.addon.dispatcher;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 19:07
 *
 * @author: e03229
 */
public interface ActionHandlerRegistry {

    /**
     * Searches the registry and returns the first handler which supports the
     * specied action, or <code>null</code> if none is available.
     *
     * @param action The action.
     * @return The handler.
     * @throws ActionNotFoundException
     */
    public <A extends Action<R>, R extends Result, E extends ExecutionContext> ActionHandler<A, R, E> findHandler(A action)
            throws ActionNotFoundException;

    /**
     * Clears all registered handlers from the registry.
     */
    public void clearHandlers();


}
