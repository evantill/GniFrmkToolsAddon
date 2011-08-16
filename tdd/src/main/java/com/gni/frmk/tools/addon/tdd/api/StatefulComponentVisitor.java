package com.gni.frmk.tools.addon.tdd.api;

import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 02/08/11
 * Time: 10:09
 *
 * @author: e03229
 */
public interface StatefulComponentVisitor extends ComponentVisitor {
    void startVisit();

    void endVisit();
}
