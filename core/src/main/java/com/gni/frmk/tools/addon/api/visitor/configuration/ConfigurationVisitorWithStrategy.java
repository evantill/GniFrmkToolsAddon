package com.gni.frmk.tools.addon.api.visitor.configuration;

import com.gni.frmk.tools.addon.api.visitor.configuration.strategy.ConfigurationVisitorStrategy;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import org.eclipse.persistence.internal.indirection.ProtectedValueHolder;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 11:04
 *
 * @author: e03229
 */
public abstract class ConfigurationVisitorWithStrategy<V extends ConfigurationVisitorWithStrategy<V>>
        implements ConfigurationVisitor {

    private final ConfigurationVisitorStrategy<V> strategy;

    public ConfigurationVisitorWithStrategy(ConfigurationVisitorStrategy<V> strategy) {
        this.strategy = strategy;
    }

    @Override
    public final void visitConfiguration(Configuration<?> visited) {
        strategy.visitConfiguration(self(), visited);
    }

    protected abstract V self();
}
