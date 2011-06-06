package com.gni.frmk.tools.addon.model.component;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 10:30
 *
 * @author: e03229
 */
public interface ComponentState<S extends ComponentState<S>> extends Comparable<S> {
    boolean exist();

    boolean unknown();
}
