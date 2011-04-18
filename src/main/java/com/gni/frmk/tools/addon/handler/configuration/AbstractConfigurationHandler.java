package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.action.configuration.ConfigurationAction;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.Dispatcher;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.api.action.ExecutionContext;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.api.configuration.ConfigurationProcessingStrategy.Operation;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationRepository;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:33
 *
 * @author: e03229
 */
public abstract class AbstractConfigurationHandler<A extends ConfigurationAction<R>, R extends ConfigurationResult, E extends ExecutionContext>
        extends RepositoryAware
        implements
        ConfigurationVisitor,
        ConfigurationProcessingContext,
        ActionHandler<A, R, E> {

    private class ConfigurationVisitorAdapter implements ConfigurationVisitor {
        @Override
        public void dispatchVisit(final ConfigurationVisited visitable) {
            AbstractConfigurationHandler.this.executeStrategy(new Operation() {
                @Override
                public ConfigurationVisitor getVisitor() {
                    return AbstractConfigurationHandler.this;
                }

                @Override
                public ConfigurationVisited getVisited() {
                    return visitable;
                }
            });
        }
    }

    private final ConfigurationVisitorAdapter cnfVisitorAdapter = new ConfigurationVisitorAdapter();
    private final ConfigurationProcessingStrategy strategy;

    protected AbstractConfigurationHandler(ConfigurationRepository repository, Dispatcher dispatcher, ConfigurationProcessingStrategy strategy) {
        super(repository, dispatcher);
        this.strategy = strategy;
    }

    @Override
    public void dispatchVisit(ConfigurationVisited visitable) {
        try {
            visitable.accept(this);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ConfigurationProcessingStrategy executeStrategy(Operation target) {
        return strategy.execute(target);
    }

    protected ConfigurationProcessingStrategy getStrategy() {
        return strategy;
    }

    @Override
    public final R execute(A action, E context) throws DispatchException {
        Configuration conf = doExtractActionFromAction(action, context);
        cnfVisitorAdapter.dispatchVisit(action.getConfiguration());
        return doPrepareResponse(action, context, conf);
    }

    protected abstract R doPrepareResponse(A action, E context, Configuration configuration);

    protected abstract Configuration doExtractActionFromAction(A action, E context);


}
