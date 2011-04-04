package com.gni.frmk.tools.addon.visitor.api;

import com.gni.frmk.tools.addon.model.component.*;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationVisitorRaisingException extends ComponentVisitorRaisingException {

    @Override
    void visit(AdapterConnection visited) throws ConfigurationVisitorException;

    @Override
    void visit(AdapterListener visited) throws ConfigurationVisitorException;

    @Override
    void visit(AdapterNotification visited) throws ConfigurationVisitorException;

    @Override
    void visit(Port visited) throws ConfigurationVisitorException;

    @Override
    void visit(Scheduler visited) throws ConfigurationVisitorException;

    @Override
    void visit(NativeTrigger visited) throws ConfigurationVisitorException;

    @Override
    void visit(JmsTrigger visited) throws ConfigurationVisitorException;

    @Override
    void visit(JmsAlias visited) throws ConfigurationVisitorException;


}
