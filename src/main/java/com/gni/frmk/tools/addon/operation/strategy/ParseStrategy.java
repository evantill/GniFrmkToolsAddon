package com.gni.frmk.tools.addon.operation.strategy;

import com.gni.frmk.tools.addon.configuration.Configuration;
import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.gni.frmk.tools.addon.configuration.components.AdapterListener;
import com.gni.frmk.tools.addon.configuration.components.AdapterNotification;
import com.gni.frmk.tools.addon.configuration.components.JmsAlias;
import com.gni.frmk.tools.addon.configuration.components.JmsTrigger;
import com.gni.frmk.tools.addon.configuration.components.NativeTrigger;
import com.gni.frmk.tools.addon.configuration.components.Port;
import com.gni.frmk.tools.addon.configuration.components.Scheduler;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public class ParseStrategy implements ConfigurationVisitorStrategy {

    private final ConfigurationVisitor visitor;

    public ParseStrategy(ConfigurationVisitor visitor) {
        this.visitor = visitor;
    }

    public void execute(Configuration cnf) {
        for (AdapterConnection element : cnf.getAdapterConnections()) {
            element.accept(visitor);
        }
        for (AdapterNotification element : cnf.getAdapterNotifications()) {
            element.accept(visitor);
        }
        for (AdapterListener element : cnf.getAdapterListeners()) {
            element.accept(visitor);
        }
        for (Port element : cnf.getPorts()) {
            element.accept(visitor);
        }
        for (Scheduler element : cnf.getSchedulers()) {
            element.accept(visitor);
        }
        for (JmsTrigger element : cnf.getJmsTriggers()) {
            element.accept(visitor);
        }
        for (NativeTrigger element : cnf.getNativeTriggers()) {
            element.accept(visitor);
        }
        for (JmsAlias element : cnf.getJmsAliases()) {
            element.accept(visitor);
        }
    }
}
