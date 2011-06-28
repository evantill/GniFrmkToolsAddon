package com.gni.frmk.tools.addon.api.visitor.component;

import com.gni.frmk.tools.addon.model.component.Component;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/05/11
 * Time: 08:37
 *
 * @author: e03229
 */
public interface ComponentVisitor {
    void visitComponent(Component<?, ?, ?,?,?> visited);
}
