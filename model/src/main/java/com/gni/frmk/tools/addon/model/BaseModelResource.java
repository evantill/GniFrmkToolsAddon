package com.gni.frmk.tools.addon.model;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:23
 *
 * @author: e03229
 */
public abstract class BaseModelResource implements ModelResource {

    protected abstract Package[] getContextPathPackages();

     @Override
    public final String getContextPath() {
        StringBuilder builder = new StringBuilder();
        for(Package p: getContextPathPackages()){
            builder.append(p.getName()).append(":");
        }
        return builder.toString();
    }
}
