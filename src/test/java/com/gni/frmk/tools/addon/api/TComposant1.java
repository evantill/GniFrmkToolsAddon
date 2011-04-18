package com.gni.frmk.tools.addon.api;

import com.gni.frmk.tools.addon.api.visitor.TComponentVisited;
import com.gni.frmk.tools.addon.api.visitor.TComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:41
 *
 * @author: e03229
 */
public class TComposant1 implements TComponentVisited {

    @Override
    public void accept(TComponentVisitor visitor) {
        System.out.println("Composant1.accept");
        visitor.visit(this);
    }
}
