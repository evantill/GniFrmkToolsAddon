package com.gni.frmk.tools.addon.api.visitor;

import com.gni.frmk.tools.addon.api.TComposant1;
import com.gni.frmk.tools.addon.api.TComposant2;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 13:24
 *
 * @author: e03229
 */
public interface TComponentVisitor extends Visitor<TComponentVisitor, TComponentVisited> {

    void visit(TComposant1 visited);

    void visit(TComposant2 visited);
}
