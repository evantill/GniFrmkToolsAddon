package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.Configuration.Builder;
import com.gni.frmk.tools.addon.model.configuration.component.AdapterConnectionConfiguration;

/**
 * Created by IntelliJ IDEA.
 * Date: 13/04/11
 * Time: 19:38
 *
 * @author: e03229
 */
public class RefreshConfigurationBuilderVisitor implements ConfigurationVisitor {

    Builder builder = Configuration.builder();

    @Override
    public void dispatchVisit(ConfigurationVisited visitable) {
        visitable.accept(this);
    }

    @Override
    public void visitConfiguration(Configuration configuration) {
        builder.from(configuration).from(configuration);
    }

    @Override
    public void visitComponent(AdapterConnection visited) {
        builder.getAdapterConnectionConfigurations().get(0).
    }

    @Override
    public void visitComponentConfiguration(AdapterConnectionConfiguration visited) {

    }
}
