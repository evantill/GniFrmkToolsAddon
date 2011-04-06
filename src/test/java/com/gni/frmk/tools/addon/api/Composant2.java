package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.service.api.component.ComponentVisited;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:41
 *
 * @author: e03229
 */
public class Composant2 implements ComponentVisited {
    @Override
    public void accept(ComponentVisitor visitor) {
        System.out.println("Composant2.accept");
        visitor.visit(this);
    }
}
