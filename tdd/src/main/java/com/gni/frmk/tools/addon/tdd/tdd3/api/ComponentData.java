package com.gni.frmk.tools.addon.tdd.tdd3.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:05
 *
 * @author: e03229
 * <p/>
 * different pour chaque plugins
 */
public interface ComponentData<D> {

    D save();

    void restore(D saved);
}
