package com.gni.frmk.tools.addon.service.configuration;

import com.gni.frmk.tools.addon.model.component.AbstractComponent;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisited;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisitor;
import com.gni.frmk.tools.addon.service.api.configuration.*;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy.Operation;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:33
 *
 * @author: e03229
 */
public abstract class AbstractService implements ComponentVisitor, ConfigurationProcessingContext, ConfigurationService {

    private class ConfigurationVisitorAdapter implements ConfigurationVisitor {
        @Override
        public void dispatchVisit(final ConfigurationVisited visitable) {
            AbstractService.this.executeStrategy(new Operation() {
                @Override
                public ComponentConfigurationVisitor getVisitor() {
                    return AbstractService.this.componentCnfVisitorAdapter;
                }

                @Override
                public ConfigurationVisited getVisited() {
                    return visitable;
                }
            });
        }
    }

    private class ComponentConfigurationVisitorAdapter implements ComponentConfigurationVisitor {
        @Override
        public <C extends AbstractComponent<?, S>, S extends AbstractComponentState>
        void visit(ComponentConfiguration<C, S> visited) {
            AbstractService.this.visitComponentConfiguration(visited);
        }

        @Override
        public void dispatchVisit(ComponentConfigurationVisited visitable) {
            visitable.accept(this);
        }
    }

    protected abstract <C extends AbstractComponent<?, S>, S extends AbstractComponentState>
    void visitComponentConfiguration(ComponentConfiguration<C, S> visited);

    private final ConfigurationVisitorAdapter cnfVisitorAdapter = new ConfigurationVisitorAdapter();
    private final ComponentConfigurationVisitorAdapter componentCnfVisitorAdapter = new ComponentConfigurationVisitorAdapter();
    private final ConfigurationProcessingStrategy strategy;

    protected AbstractService(ConfigurationProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void dispatchVisit(ComponentVisited visitable) {
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
}
