package com.gni.frmk.tools.addon.api.visitor.test;

import com.gni.frmk.tools.addon.api.visitor.ComponentVisitor;
import com.gni.frmk.tools.addon.model.component.test.Component1;
import com.gni.frmk.tools.addon.model.component.test.Component2;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 10:18
 *
 * @author: e03229
 */
public interface TestComponentVisitor extends ComponentVisitor {
    void visitComponentComponent1(Component1 visited);
    void visitComponentComponent2(Component2 visited);
}
