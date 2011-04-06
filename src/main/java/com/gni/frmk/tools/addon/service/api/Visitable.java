package com.gni.frmk.tools.addon.service.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:10
 *
 * @author: e03229
 */
public interface Visitable<V extends Visitor<V, T>, T extends Visitable<V, T>> {

    public void accept(V visitor);

}