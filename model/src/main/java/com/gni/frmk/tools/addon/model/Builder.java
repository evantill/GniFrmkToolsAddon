package com.gni.frmk.tools.addon.model;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/06/11
 * Time: 17:37
 *
 * @author: e03229
 */
public interface Builder<B extends Builder<B, T>, T> {

    T build() throws BuildException;

    B self();

    public static class BuildException extends RuntimeException{
    }
}
