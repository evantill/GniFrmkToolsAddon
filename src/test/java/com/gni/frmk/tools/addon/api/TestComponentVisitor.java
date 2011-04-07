package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.service.api.Visitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:24
 *
 * @author: e03229
 */
public interface TestComponentVisitor extends Visitor<TestComponentVisitor, TestComponentVisited> {

    void visit(TestComposant1 visited);

    void visit(TestComposant2 visited);
}
