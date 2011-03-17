package com.gni.frmk.tools.addon.configuration.visitors;

import com.gni.frmk.tools.addon.configuration.components.*;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
public interface ComponentVisitorRaisingException {

    void visit(AdapterConnection visited)throws ComponentVisitorException;

    void visit(AdapterListener visited)throws ComponentVisitorException;

    void visit(AdapterNotification visited)throws ComponentVisitorException;

    void visit(Port visited)throws ComponentVisitorException;

    void visit(Scheduler visited)throws ComponentVisitorException;

    void visit(NativeTrigger visited)throws ComponentVisitorException;

    void visit(JmsTrigger visited) throws ComponentVisitorException;

    void visit(JmsAlias visited) throws ComponentVisitorException;

    void visit(IntegrationServerPackage visited) throws ComponentVisitorException;

}
