package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 15:04
 * <p/>
 * Utilise comme memento pour le component
 * pourra etre enregistre dans un xml ou dans une base
 *
 * @author: e03229
 */
public interface ComponentState<T extends ComponentState> extends Comparable<T> {
}
