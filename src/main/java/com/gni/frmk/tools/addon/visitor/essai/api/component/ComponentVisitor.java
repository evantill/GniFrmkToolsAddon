package com.gni.frmk.tools.addon.visitor.essai.api.component;

import com.gni.frmk.tools.addon.visitor.essai.api.Visitor;
import com.gni.frmk.tools.addon.visitor.essai.impl.Composant1;
import com.gni.frmk.tools.addon.visitor.essai.impl.Composant2;
import com.gni.frmk.tools.addon.visitor.essai.impl.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:35
 *
 * @author: e03229
 */
public interface ComponentVisitor extends Visitor<ComponentVisitor,ComponentVisited> {

    void visit(Composant1 visitable);
    //...
    void visit(Composant2 visitable);
}
