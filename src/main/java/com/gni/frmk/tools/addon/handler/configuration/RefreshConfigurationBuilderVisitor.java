package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterConnection;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration.MutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.component.AdapterConnectionConfiguration;

/**
 * Created by IntelliJ IDEA.
 * Date: 13/04/11
 * Time: 19:38
 *
 * @author: e03229
 */
public class RefreshConfigurationBuilderVisitor implements ConfigurationVisitor {

    MutableConfiguration builder = ImmutableConfiguration.builder();

    @Override
    public void dispatchVisit(ConfigurationVisited visitable) {
        visitable.accept(this);
    }

    @Override
    public void visitConfiguration(ImmutableConfiguration configuration) {
        builder.from(configuration).from(configuration);
    }

    @Override
    public void visitComponent(ImmutableAdapterConnection visited) {
        builder.getAdapterConnectionConfigurations().get(0).
    }

    @Override
    public void visitComponentConfiguration(AdapterConnectionConfiguration visited) {

    }
}
