package com.gni.frmk.tools.addon.visitor.essai.impl;

import com.gni.frmk.tools.addon.visitor.essai.api.component.ComponentVisited;
import com.gni.frmk.tools.addon.visitor.essai.api.component.ComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:41
 *
 * @author: e03229
 */
public class Composant1 implements ComponentVisited{

    @Override
    public void accept(ComponentVisitor visitor) {
        System.out.println("Composant1.accept");
        visitor.visit(this);
    }
}
