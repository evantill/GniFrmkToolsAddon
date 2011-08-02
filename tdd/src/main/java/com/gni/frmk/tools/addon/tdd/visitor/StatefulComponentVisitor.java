package com.gni.frmk.tools.addon.tdd.visitor;

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
