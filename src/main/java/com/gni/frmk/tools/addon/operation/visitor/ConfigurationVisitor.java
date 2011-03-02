package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.adapter.AdapterConnection;
import com.gni.frmk.tools.addon.data.adapter.AdapterNotification;
import com.gni.frmk.tools.addon.data.adapter.AdapterListener;
import com.gni.frmk.tools.addon.data.port.Port;
import com.gni.frmk.tools.addon.data.scheduler.Scheduler;
import com.gni.frmk.tools.addon.data.trigger.JmsAlias;
import com.gni.frmk.tools.addon.data.trigger.JmsTrigger;
import com.gni.frmk.tools.addon.data.trigger.NativeTrigger;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationVisitor extends ConfigurationVisitorRaisingException {
    void visit(AdapterConnection visited);

    void visit(AdapterListener visited);

    void visit(AdapterNotification visited);

    void visit(Port visited);

    void visit(Scheduler visited);

    void visit(JmsAlias visited);

    void visit(JmsTrigger visited);

    void visit(NativeTrigger visited);

    void clear();
}
