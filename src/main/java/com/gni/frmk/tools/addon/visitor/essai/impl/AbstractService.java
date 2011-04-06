package com.gni.frmk.tools.addon.visitor.essai.impl;

import com.gni.frmk.tools.addon.visitor.essai.api.component.ComponentVisited;
import com.gni.frmk.tools.addon.visitor.essai.api.component.ComponentVisitor;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationProcessingContext;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationProcessingStrategy.Operation;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationService;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationVisited;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:33
 *
 * @author: e03229
 */
public abstract class AbstractService implements ComponentVisitor, ConfigurationProcessingContext, ConfigurationService {

    private class InternalConfigurationVisitor implements ConfigurationVisitor {
        private final ComponentVisitor visitor;
        private final ConfigurationProcessingContext context;

        private InternalConfigurationVisitor(ComponentVisitor visitor, ConfigurationProcessingContext context) {
            this.visitor = visitor;
            this.context = context;
        }

        @Override
        public void dispatchVisit(final ConfigurationVisited visitable) {
            System.out.println("Service$InternalConfigurationVisitor.dispatchVisit");
            context.executeStrategy(new Operation() {
                @Override
                public ComponentVisitor getVisitor() {
                    return visitor;
                }

                @Override
                public ConfigurationVisited getVisited() {
                    return visitable;
                }
            });
        }
    }

    private final InternalConfigurationVisitor internal;
    private final ConfigurationProcessingStrategy strategy;

    protected AbstractService(ConfigurationProcessingStrategy strategy) {
        this.strategy = strategy;
        this.internal = new InternalConfigurationVisitor(this, this);
    }

    @Override
    public void dispatchVisit(ComponentVisited visitable) {
        System.out.println("Service.dispatchVisit");
        visitable.accept(this);
    }

    @Override
    public ConfigurationProcessingStrategy executeStrategy(Operation target) {
        System.out.println("Service.executeStrategy");
        return strategy.execute(target);
    }

    @Override
    public void execute(ConfigurationVisited target) {
        System.out.println("Service.execute");
        internal.dispatchVisit(target);
    }
}
