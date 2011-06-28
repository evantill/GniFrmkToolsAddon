package com.gni.frmk.tools.addon.api.visitor;

import com.gni.frmk.tools.addon.model.component.ComponentType;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 13:19
 *
 * @author: e03229
 */
public interface ComponentTypeVisitor<T extends VisitorContext> {
    void visitComponentType(ComponentType visited,T context);
}
