package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.component.*;

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
