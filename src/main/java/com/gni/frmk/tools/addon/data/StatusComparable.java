package com.gni.frmk.tools.addon.data;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 19:54
 * To change this template use File | Settings | File Templates.
 */
public interface StatusComparable<T> {

    /**
     * Note: this class has a natural ordering that is inconsistent with equals.
     *
     * @param that element to compare
     * @return 0 if same status
     */
    int compareStatusTo(T that);
}
