package com.gni.frmk.tools.addon.operation.strategy;

import com.gni.frmk.tools.addon.data.Configuration;
import com.gni.frmk.tools.addon.data.adapter.AdapterConnection;
import com.gni.frmk.tools.addon.data.trigger.JmsAlias;
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
        for (JmsAlias element : cnf.getJmsAliasList()) {
            element.accept(visitor);
        }
        for (AdapterConnection element : cnf.getAdapterConnectionList()) {
            element.accept(visitor);
        }
    }
}

