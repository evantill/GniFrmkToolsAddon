package com.gni.frmk.tools.addon.service.configuration;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.Result;
import com.gni.frmk.tools.addon.service.api.configuration.*;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy.Operation;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:33
 *
 * @author: e03229
 */
public abstract class AbstractService implements ComponentConfigurationVisitor, ConfigurationProcessingContext, ConfigurationService {

    private class ConfigurationVisitorAdapter implements ConfigurationVisitor {
        @Override
        public void dispatchVisit(final ConfigurationVisited visitable) {
            AbstractService.this.executeStrategy(new Operation() {
                @Override
                public ComponentConfigurationVisitor getVisitor() {
                    return AbstractService.this;
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

    protected AbstractService(ConfigurationProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void dispatchVisit(ComponentConfigurationVisited visitable) {
        visitable.accept(this);
    }

    @Override
    public ConfigurationProcessingStrategy executeStrategy(Operation target) {
        return strategy.execute(target);
    }

    @Override
    public void execute(ConfigurationVisited target) {
        cnfVisitorAdapter.dispatchVisit(target);
    }

    public abstract <A extends Action<R>, R extends Result> R dispatch(A command);
}
