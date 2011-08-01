package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/08/11
 * Time: 16:18
 *
 * @author: e03229
 */
public interface ComponentVisitor {
    void visitComponent(Component<?, ?> component);
}
