package com.gni.frmk.tools.addon.operation.strategy;

import com.gni.frmk.tools.addon.data.Configuration;
import com.gni.frmk.tools.addon.data.adapter.AdapterListener;
import com.gni.frmk.tools.addon.data.adapter.AdapterNotification;
import com.gni.frmk.tools.addon.data.port.Port;
import com.gni.frmk.tools.addon.data.scheduler.Scheduler;
import com.gni.frmk.tools.addon.data.trigger.JmsTrigger;
import com.gni.frmk.tools.addon.data.trigger.NativeTrigger;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:37
 * To change this template use File | Settings | File Templates.
 */
public class OpenInputStrategy implements ConfigurationVisitorStrategy {

    private final ConfigurationVisitor visitor;

    public OpenInputStrategy(ConfigurationVisitor visitor) {
        this.visitor = visitor;
    }

    public void execute(Configuration cnf) {
        for (AdapterListener element : cnf.getAdapterListenerList()) {
            element.accept(visitor);
        }
        for (AdapterNotification element : cnf.getAdapterNotificationList()) {
            element.accept(visitor);
        }
        for (NativeTrigger element : cnf.getNativeTriggerList()) {
            element.accept(visitor);
        }
        for (JmsTrigger element : cnf.getJmsTriggerList()) {
            element.accept(visitor);
        }

        for (Scheduler element : cnf.getSchedulerList()) {
            element.accept(visitor);
        }
        for (Port element : cnf.getPortList()) {
            element.accept(visitor);
        }
    }
}
