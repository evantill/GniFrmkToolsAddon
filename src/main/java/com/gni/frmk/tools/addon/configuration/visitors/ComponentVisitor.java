package com.gni.frmk.tools.addon.configuration.visitors;

import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
public interface ComponentVisitor extends ComponentVisitorRaisingException {
    void visit(AdapterConnection visited);
}
