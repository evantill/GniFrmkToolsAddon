package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:03
 *
 * @author: e03229
 */
public interface TypedComponentVisitor extends ComponentVisitor{

    void visitAdapterConnection(AdapterConnection visited);

    void visitAdapterListener(AdapterListener visited);

    void visitAdapterNotification(AdapterNotification visited);

    void visitPort(Port visited);

    void visitScheduler(Scheduler visited);

    void visitNativeTriger(NativeTrigger visited);

    void visitJmsAlias(JmsAlias visited);

    void visitJmsTrigger(JmsTrigger visited);
}
