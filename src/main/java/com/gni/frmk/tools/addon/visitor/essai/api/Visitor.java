package com.gni.frmk.tools.addon.visitor.essai.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:10
 *
 * @author: e03229
 */
public interface Visitor<V extends Visitor<V, T>, T extends Visitable<V, T>> {

    void dispatchVisit(T visitable);

}