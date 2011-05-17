package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.model.component.Component;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:02
 *
 * @author: e03229
 */
public interface ComponentVisitor {
     void visitComponent(Component<?, ?, ?> visited);
}
