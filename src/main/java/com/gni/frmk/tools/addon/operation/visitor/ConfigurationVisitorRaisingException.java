package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.adapter.AdapterListener;
import com.gni.frmk.tools.addon.data.adapter.AdapterConnection;
import com.gni.frmk.tools.addon.data.adapter.AdapterNotification;
import com.gni.frmk.tools.addon.data.port.Port;
import com.gni.frmk.tools.addon.data.scheduler.Scheduler;
import com.gni.frmk.tools.addon.data.trigger.JmsAlias;
import com.gni.frmk.tools.addon.data.trigger.JmsTrigger;
import com.gni.frmk.tools.addon.data.trigger.NativeTrigger;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationVisitorRaisingException {
    void visit(AdapterConnection visited) throws ConfigurationVisitorException;

    void visit(AdapterListener visited) throws ConfigurationVisitorException;

    void visit(AdapterNotification visited) throws ConfigurationVisitorException;

    void visit(Port visited) throws ConfigurationVisitorException;

    void visit(Scheduler visited) throws ConfigurationVisitorException;

    void visit(JmsAlias visited) throws ConfigurationVisitorException;

    void visit(JmsTrigger visited) throws ConfigurationVisitorException;

    void visit(NativeTrigger visited) throws ConfigurationVisitorException;
}
