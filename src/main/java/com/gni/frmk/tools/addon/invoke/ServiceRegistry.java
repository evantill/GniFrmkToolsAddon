package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.ActionPattern.Action;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ExecutionContext;
import com.gni.frmk.tools.addon.invoke.ActionPattern.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 19:07
 *
 * @author: e03229
 */
public interface ServiceRegistry {

    /**
     * Searches the registry and returns the first handler which supports the
     * specied action, or <code>null</code> if none is available.
     *
     * @param action The action.
     * @return The handler.
     */
    public <A extends Action<R>, R extends Result, E extends ExecutionContext> ActionHandler<A, R, E> findHandler(A action)
            throws ServiceNotFoundException;

    /**
     * Clears all registered handlers from the registry.
     */
    public void clearHandlers();

    public static class ServiceNotFoundException extends ServiceRuntimeException {

        public ServiceNotFoundException(Action action) {
            super(action, "service not found");
        }

    }

    public static class ServiceRuntimeException extends RuntimeException {

        private final Action<?> action;
        private final String causeMessage;

        public ServiceRuntimeException(Action<?> action, String causeMessage) {
            this.action= action;
            this.causeMessage = causeMessage;
        }

        public ServiceRuntimeException(Action<?> action, RuntimeException ex) {
            super(ex);
            this.action= action;
            causeMessage = ex.getMessage();
        }

        @Override
        public String getMessage() {
            return String.format("ServiceRuntimeException on %s cause: %s", action.getClass().getName(), causeMessage);
        }
    }


}
