package com.gni.frmk.tools.addon.tdd.visitor;

import com.gni.frmk.tools.addon.tdd.api.Component;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/08/11
 * Time: 16:18
 *
 * @author: e03229
 */
public interface ComponentVisitor {
    void visitComponent(Component<?, ?> visited);
}
