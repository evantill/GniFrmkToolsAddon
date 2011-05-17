package com.gni.frmk.tools.addon.operation.handler.configuration;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.api.Result;
import com.gni.frmk.tools.addon.repository.ConfigurationRepository;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 13:54
 *
 * @author: e03229
 */
public abstract class RepositoryAware {
    private final ConfigurationRepository repository;
    private final Dispatcher dispatcher;

    protected RepositoryAware(ConfigurationRepository repository, Dispatcher dispatcher) {
        this.repository = repository;
        this.dispatcher = dispatcher;
    }

    public ConfigurationRepository getRepository() {
        return repository;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    protected final <A extends Action<R>, R extends Result> R dispatch(A command) throws DispatchException {
        return dispatcher.execute(command);
    }
}
