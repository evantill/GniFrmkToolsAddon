package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.service.api.component.ComponentVisitor;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisited;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:41
 *
 * @author: e03229
 */
public class Composant1 implements ComponentVisited {

    @Override
    public void accept(ComponentVisitor visitor) {
        System.out.println("Composant1.accept");
        visitor.visit(this);
    }
}
