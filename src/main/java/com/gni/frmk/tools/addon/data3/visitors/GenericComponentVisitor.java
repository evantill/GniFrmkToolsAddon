package com.gni.frmk.tools.addon.data3.visitors;

import com.gni.frmk.tools.addon.data3.components.Component;
import com.gni.frmk.tools.addon.data3.components.integrationServer.AdapterConnection;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericComponentVisitor implements ComponentVisitor {

    public abstract void visitAny(Component visited);

    @Override
    public void visit(AdapterConnection visited) {
        visitAny(visited);
    }
}
