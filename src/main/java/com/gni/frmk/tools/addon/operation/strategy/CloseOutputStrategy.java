package com.gni.frmk.tools.addon.operation.strategy;

import com.gni.frmk.tools.addon.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.configuration.Configuration;
import com.gni.frmk.tools.addon.configuration.component.AdapterConnection;
import com.gni.frmk.tools.addon.configuration.component.ConnectableState;
import com.gni.frmk.tools.addon.configuration.component.EnableState;
import com.gni.frmk.tools.addon.configuration.component.JmsAlias;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class CloseOutputStrategy implements ConfigurationVisitorStrategy {

    private final ConfigurationVisitor visitor;

    public CloseOutputStrategy(ConfigurationVisitor visitor) {
        this.visitor = visitor;
    }

    public void execute(Configuration cnf) {
        for (ComponentConfiguration<JmsAlias, ConnectableState> element : cnf.getJmsAliasConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<AdapterConnection,EnableState> element : cnf.getAdapterConnectionConfigurations()) {
            element.getComponent().accept(visitor);
        }
    }
}

