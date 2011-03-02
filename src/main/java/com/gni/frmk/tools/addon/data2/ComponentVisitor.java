package com.gni.frmk.tools.addon.data2;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 09:37
 * To change this template use File | Settings | File Templates.
 */
public interface ComponentVisitor {
    void visit(AdapterConnection visited);

    void visit(AdapterNotification visited);

    void visit(AdapterListener visited);

    void visit(Port visited);

    void visit(Scheduler visited);

    void visit(JmsAlias visited);

    void visit(JmsTrigger visited);

    void visit(NativeTrigger visited);
}
