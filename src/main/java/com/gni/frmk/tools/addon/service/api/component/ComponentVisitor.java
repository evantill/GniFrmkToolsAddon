package com.gni.frmk.tools.addon.service.api.component;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.service.api.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
public interface ComponentVisitor  extends Visitor<ComponentVisitor,ComponentVisited> {

    void visit(AdapterConnection visited);

    void visit(AdapterListener visited);

    void visit(AdapterNotification visited);

    void visit(Port visited);

    void visit(Scheduler visited);

    void visit(NativeTrigger visited);

    void visit(JmsTrigger visited);

    void visit(JmsAlias visited);

    void visit(IntegrationServerPackage visited);
}
