package com.gni.frmk.tools.addon.tdd.impl.component;

import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/08/11
 * Time: 21:06
 *
 * @author: e03229
 */
public abstract class StatefulComponentVisitorFactory {

    public ComponentVisitor newComponentVisitor() {
        StatefulComponentVisitor visitor = newStatefulComponentVisitor();
        return visitor.newVisitorContext();
    }

    protected abstract StatefulComponentVisitor newStatefulComponentVisitor();
}
